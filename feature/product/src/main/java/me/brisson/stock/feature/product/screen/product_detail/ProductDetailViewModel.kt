package me.brisson.stock.feature.product.screen.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import me.brisson.stock.core.data.repository.ProductRepository
import me.brisson.stock.core.model.Product
import me.brisson.stock.feature.product.navigation.productIdArgs
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository,
) : ViewModel() {
    private val productId: Int = checkNotNull(savedStateHandle[productIdArgs])
    val product: StateFlow<Product?> = productRepository.loadById(productId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), initialValue = null)



}
