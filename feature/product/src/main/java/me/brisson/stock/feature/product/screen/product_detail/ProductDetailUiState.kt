package me.brisson.stock.feature.product.screen.product_detail

import me.brisson.stock.core.model.Product
import me.brisson.stock.core.model.StockItem
import me.brisson.stock.core.model.StockMovement

sealed interface ProductDetailUiState {
    data object Loading : ProductDetailUiState

    data class Success(
        val product: Product,
        val stockItems: List<StockItem>,
        val stockMovements: List<StockMovement>,
    ) : ProductDetailUiState

    data class Error(val error: Throwable) : ProductDetailUiState
}
