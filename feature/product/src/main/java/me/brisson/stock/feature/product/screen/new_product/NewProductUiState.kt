package me.brisson.stock.feature.product.screen.new_product

sealed interface NewProductUiState {
    data object Loading: NewProductUiState
    data object Success: NewProductUiState
    data object Error: NewProductUiState
    data object Idle: NewProductUiState
}

fun NewProductUiState.isLoading() = this == NewProductUiState.Loading
fun NewProductUiState.isSuccess() = this == NewProductUiState.Success