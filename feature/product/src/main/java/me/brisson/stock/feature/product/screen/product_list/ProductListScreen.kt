package me.brisson.stock.feature.product.screen.product_list

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.brisson.stock.core.design_system.theme.StockTheme
import me.brisson.stock.core.model.Product
import me.brisson.stock.feature.product.screen.product_list.components.ProductItem
import me.brisson.stock.feature.product.screen.product_list.components.ProductListEmptyState

@Composable
fun ProductListRoute(
    modifier: Modifier = Modifier,
    viewModel: ProductListViewModel = hiltViewModel(),
    onNewProduct: () -> Unit,
) {
    val productListUiState by viewModel.productListUiState.collectAsStateWithLifecycle()

    ProductListScreen(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        productListUiState = productListUiState,
        onNewProduct = onNewProduct,
        onProduct = { /*TODO*/ },
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun ProductListScreen(
    modifier: Modifier = Modifier,
    productListUiState: ProductListUiState,
    onNewProduct: () -> Unit,
    onProduct: (product: Product) -> Unit,
) {
    Column(modifier = modifier) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(text = "Produtos", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable { onNewProduct() }
                    .padding(vertical = 4.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                )

                Text(
                    text = "Adicionar novo",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }

        AnimatedContent(targetState = productListUiState, label = "") { uiState ->
            when (uiState) {
                is ProductListUiState.Success -> {
                    if (uiState.productList.isNotEmpty()) {
                        LazyColumn(
                            contentPadding = PaddingValues(top = 32.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(uiState.productList) { product ->
                                ProductItem(
                                    modifier = Modifier.fillMaxWidth(),
                                    product = product,
                                    onClick = { onProduct(product) },
                                )
                            }
                        }
                    } else {
                        ProductListEmptyState(modifier = Modifier.padding(top = 125.dp))
                    }
                }
                is ProductListUiState.Error -> {
                    Text("vixi")
                }
                else -> Unit
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProductListScreen() {
    val productListUiState = ProductListUiState.Success(emptyList())

    StockTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ProductListScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                productListUiState = productListUiState,
                onNewProduct = { },
                onProduct = { },
            )
        }
    }
}
