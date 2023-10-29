package me.brisson.stock.feature.product.screen.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.brisson.stock.core.data.repository.ProductRepository
import me.brisson.stock.core.model.MeasurementUnit
import me.brisson.stock.core.model.Product
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {
    val productListUiState: StateFlow<ProductListUiState> = productRepository.getAll()
        .catch { ProductListUiState.Error(it) }
        .map { ProductListUiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ProductListUiState.Loading
        )

    fun addNewProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            val product = Product(
                name = "Teste",
                measurementUnit = MeasurementUnit.UNIT,
                expirationDate = null,
                observation = null,
            )

            productRepository.add(product)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.delete(product)
        }
    }
}
