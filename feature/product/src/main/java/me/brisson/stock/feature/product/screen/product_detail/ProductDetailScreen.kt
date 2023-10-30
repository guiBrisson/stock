package me.brisson.stock.feature.product.screen.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.brisson.stock.core.design_system.components.MyDropdown
import me.brisson.stock.core.design_system.theme.StockTheme
import me.brisson.stock.core.model.MeasurementUnit
import me.brisson.stock.core.model.Product
import me.brisson.stock.core.model.StockItem
import me.brisson.stock.feature.product.screen.product_detail.components.MovementsButtons
import me.brisson.stock.feature.product.screen.product_detail.components.ProductDetailTab
import me.brisson.stock.feature.product.screen.product_detail.components.ProductDetails
import me.brisson.stock.feature.product.screen.product_detail.components.ProductTabs
import java.util.Date

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    productDetailUiState: ProductDetailUiState,
    onNewEntry: (StockItem) -> Unit,
    onBack: () -> Unit,
) {
    var selectedTab: ProductDetailTab by rememberSaveable { mutableStateOf(ProductDetailTab.STOCK) }

    Scaffold(
        modifier = modifier,
        topBar = {
            Box {
                var showMoreOptionsDropdown by remember { mutableStateOf(false) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }

                    IconButton(onClick = { showMoreOptionsDropdown = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                    }
                }

                MoreOptionsDropdown(
                    expanded = showMoreOptionsDropdown,
                    onDismissRequest = { showMoreOptionsDropdown = false },
                    onEdit = { /*TODO*/ },
                    onDelete = { /*TODO*/ },
                )
            }
        }
    ) { scaffoldPadding ->
        Column(modifier = Modifier.padding(scaffoldPadding)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
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
                                    .padding(vertical = 16.dp, horizontal = 20.dp),
                                productDetailUiState = productDetailUiState,
                            )
                        }

                        item {
                            ProductTabs(
                                modifier = Modifier
                                    .padding(top = 32.dp)
                                    .height(40.dp)
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp),
                                selectedTab = selectedTab,
                                onTabSelected = { selectedTab = it },
                            )
                        }

                        when (selectedTab) {
                            ProductDetailTab.STOCK -> {
                                items(productDetailUiState.stockItems) { stockItem ->
                                    Text(text = stockItem.batch)
                                }
                            }

                            ProductDetailTab.MOVEMENT -> {
                                items(productDetailUiState.stockMovements) { movements ->
                                    Text(text = movements.itemBatch)
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

            MovementsButtons(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                onWriteOff = { /*TODO*/ },
                onNewEntry = {
                    when (productDetailUiState) {
                        is ProductDetailUiState.Success -> {
                            val item = StockItem(
                                batch = "123123",
                                productId = productDetailUiState.product.id,
                                entryDate = Date(),
                                price = 17f,
                                expirationDate = Date(),
                                quantity = 10,
                            )

                            onNewEntry(item)
                        }

                        else -> Unit
                    }
                },
            )

        }
    }

}

@Composable
private fun MoreOptionsDropdown(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    MyDropdown(
        modifier = modifier.width(IntrinsicSize.Min),
        alignment = Alignment.TopEnd,
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        offset = IntOffset(y = 150, x = 0),
    ) {
        DropdownMenuItem(text = { Text(text = "Editar") }, onClick = onEdit)
        DropdownMenuItem(text = { Text(text = "Excluir") }, onClick = onDelete)
    }
}

@Preview
@Composable
private fun PreviewProductDetailScreen() {
    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val product = Product(
                name = "Arroz branco",
                measurementUnit = MeasurementUnit.MASS,
                expirationDay = null,
                observation = "this is an observation!",
            )

            ProductDetailScreen(
                modifier = Modifier.fillMaxSize(),
                productDetailUiState = ProductDetailUiState.Loading,
                onNewEntry = { },
                onBack = { },
            )
        }
    }
}
