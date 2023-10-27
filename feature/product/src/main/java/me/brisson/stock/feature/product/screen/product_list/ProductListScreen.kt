package me.brisson.stock.feature.product.screen.product_list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProductListRoute(
    modifier: Modifier = Modifier,
    viewModel: ProductListViewModel = hiltViewModel(),
) {
    val productListUiState by viewModel.productListUiState.collectAsStateWithLifecycle()

    ProductListScreen(
        modifier = modifier,
        productListUiState = productListUiState,
    )
}

@Composable
internal fun ProductListScreen(
    modifier: Modifier = Modifier,
    productListUiState: ProductListUiState,
) {
    Column(modifier = modifier) {
        Text(text = "this is a test")
    }
}
