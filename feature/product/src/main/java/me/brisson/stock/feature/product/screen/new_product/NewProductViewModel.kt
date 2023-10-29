package me.brisson.stock.feature.product.screen.new_product


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.brisson.stock.core.data.repository.ProductRepository
import me.brisson.stock.core.model.Product
import javax.inject.Inject

@HiltViewModel
class NewProductViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {
    private val _newProductUiState = MutableStateFlow<NewProductUiState>(NewProductUiState.Idle)
    val newProductUiState: StateFlow<NewProductUiState> = _newProductUiState.asStateFlow()

    fun newProduct(product: Product) {
        _newProductUiState.update { NewProductUiState.Loading }
        viewModelScope.launch(Dispatchers.IO) {
            if (productRepository.add(product)) {
                _newProductUiState.update { NewProductUiState.Success }
            } else {
                _newProductUiState.update { NewProductUiState.Error }
            }
        }
    }
}
