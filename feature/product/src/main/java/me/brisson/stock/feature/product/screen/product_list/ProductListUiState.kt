package me.brisson.stock.feature.product.screen.product_list

import me.brisson.stock.core.model.Product

sealed interface ProductListUiState {
    data object Loading: ProductListUiState
    data class Success(val productList: List<Product>): ProductListUiState
    data class Error(val throwable: Throwable): ProductListUiState
}
