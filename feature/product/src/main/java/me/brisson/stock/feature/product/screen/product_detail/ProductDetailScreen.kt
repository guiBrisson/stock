package me.brisson.stock.feature.product.screen.product_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.brisson.stock.core.design_system.theme.StockTheme
import me.brisson.stock.core.model.Product
import me.brisson.stock.core.model.StockItem
import me.brisson.stock.feature.product.screen.product_detail.components.MovementsButtons
import me.brisson.stock.feature.product.screen.product_detail.components.NewEntryBottomSheet
import me.brisson.stock.feature.product.screen.product_detail.components.ProductDetailTab
import me.brisson.stock.feature.product.screen.product_detail.components.ProductDetailTopBar
import me.brisson.stock.feature.product.screen.product_detail.components.ProductDetails
import me.brisson.stock.feature.product.screen.product_detail.components.ProductMovementItem
import me.brisson.stock.feature.product.screen.product_detail.components.ProductStockHeader
import me.brisson.stock.feature.product.screen.product_detail.components.ProductStockItem
import me.brisson.stock.feature.product.screen.product_detail.components.ProductTabs
import me.brisson.stock.feature.product.util.makeList

@Composable
fun ProductDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val productDetailUiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductDetailScreen(
        modifier = modifier.fillMaxSize(),
        productDetailUiState = productDetailUiState,
        onNewEntry = viewModel::newStockItem,
        onBack = onBack,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    productDetailUiState: ProductDetailUiState,
    onNewEntry: (StockItem) -> Unit,
    onBack: () -> Unit,
) {
    val scrollState = rememberLazyListState()

    var selectedTab: ProductDetailTab by rememberSaveable { mutableStateOf(ProductDetailTab.STOCK) }

    val titleVisible by remember { derivedStateOf { scrollState.firstVisibleItemIndex > 0 } }
    var showNewEntryBottomSheet by remember { mutableStateOf(false) }
    var showWriteOffBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            val productName = when (productDetailUiState) {
                is ProductDetailUiState.Success -> productDetailUiState.product.name
                else -> ""
            }

            ProductDetailTopBar(
                modifier = Modifier.fillMaxWidth(),
                titleVisible = titleVisible,
                productName = productName,
                onBack = onBack,
                onEdit = { /*TODO*/ },
                onDelete = { /*TODO*/ },
            )
        }
    ) { scaffoldPadding ->
        Column(modifier = Modifier.padding(scaffoldPadding)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                state = scrollState,
            ) {
                when (productDetailUiState) {
                    is ProductDetailUiState.Success -> {
                        item {
                            Row(
                                modifier = Modifier.padding(horizontal = 20.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                productDetailUiState.product.observation?.let {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                                    )
                                }

                                Text(
                                    text = productDetailUiState.product.name,
                                    style = MaterialTheme.typography.titleLarge,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }

                        item {
                            ProductDetails(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 16.dp,
                                        start = 20.dp,
                                        end = 20.dp,
                                        bottom = 32.dp
                                    ),
                                productDetailUiState = productDetailUiState,
                            )
                        }

                        stickyHeader {
                            ProductTabs(
                                modifier = Modifier
                                    .height(40.dp)
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.background)
                                    .padding(horizontal = 20.dp),
                                selectedTab = selectedTab,
                                onTabSelected = { selectedTab = it },
                            )

                            ProductStockHeader(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.background)
                                    .padding(start = 20.dp, end = 20.dp, top = 12.dp),
                                tab = selectedTab,
                            )
                        }

                        when (selectedTab) {
                            ProductDetailTab.STOCK -> {
                                itemsIndexed(productDetailUiState.stockItems) { i, stockItem ->
                                    ProductStockItem(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 20.dp, vertical = 10.dp),
                                        index = i + 1,
                                        item = stockItem,
                                        measurementUnit = productDetailUiState.product.measurementUnit,
                                    )
                                }
                            }

                            ProductDetailTab.MOVEMENT -> {
                                items(productDetailUiState.stockMovements) { movement ->
                                    ProductMovementItem(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 20.dp, vertical = 10.dp),
                                        item = movement,
                                        measurementUnit = productDetailUiState.product.measurementUnit,
                                    )
                                }
                            }
                        }
                    }

                    is ProductDetailUiState.Error -> {
                        item {
                            Text(text = productDetailUiState.error.message ?: "vixii")
                        }
                    }

                    ProductDetailUiState.Loading -> {
                        item { LinearProgressIndicator(modifier = Modifier.fillMaxWidth()) }
                    }
                }
            }

            when (productDetailUiState) {
                is ProductDetailUiState.Success -> {
                    NewEntryBottomSheet(
                        show = showNewEntryBottomSheet,
                        productId = productDetailUiState.product.id,
                        measurementUnit = productDetailUiState.product.measurementUnit,
                        onDismissRequest = { showNewEntryBottomSheet = false },
                        onConclude = { onNewEntry(it); showNewEntryBottomSheet = false },
                    )

                    MovementsButtons(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(start = 20.dp, end = 20.dp, bottom = 8.dp),
                        onWriteOff = { showWriteOffBottomSheet = true },
                        onNewEntry = { showNewEntryBottomSheet = true }
                    )
                }

                else -> Unit
            }


        }
    }
}

@Preview
@Composable
private fun PreviewProductDetailScreen() {
    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val uiState = ProductDetailUiState.Success(
                product = Product.mockForPreview(),
                stockItems = makeList(StockItem.mockForPreview(), 10),
                stockMovements = emptyList(),
            )
            ProductDetailScreen(
                modifier = Modifier.fillMaxSize(),
                productDetailUiState = uiState,
                onNewEntry = { },
                onBack = { },
            )
        }
    }
}
