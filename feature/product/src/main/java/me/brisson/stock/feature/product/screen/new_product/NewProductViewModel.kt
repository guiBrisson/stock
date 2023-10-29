package me.brisson.stock.feature.product.screen.new_product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.brisson.stock.core.data.repository.ProductRepository
import me.brisson.stock.core.model.Product
import javax.inject.Inject

@HiltViewModel
class NewProductViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {

    fun newProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.add(product)
        }
    }
}
