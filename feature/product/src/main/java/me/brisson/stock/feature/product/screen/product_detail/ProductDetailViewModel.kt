package me.brisson.stock.feature.product.screen.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.brisson.stock.core.data.repository.ProductRepository
import me.brisson.stock.core.data.repository.StockItemRepository
import me.brisson.stock.core.data.repository.StockMovementRepository
import me.brisson.stock.core.model.Product
import me.brisson.stock.core.model.StockItem
import me.brisson.stock.core.model.StockMovement
import me.brisson.stock.feature.product.navigation.productIdArgs
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository,
    private val stockItemRepository: StockItemRepository,
    private val movementRepository: StockMovementRepository,
) : ViewModel() {
    private val productId: Int = checkNotNull(savedStateHandle[productIdArgs])
    private val product: StateFlow<Product?> = productRepository.loadById(productId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initialValue = null)

    private val stockItems: StateFlow<List<StockItem>> =
        stockItemRepository.loadFromProductId(productId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initialValue = emptyList())

    private val movementItems: StateFlow<List<StockMovement>> =
        movementRepository.loadFromProductId(productId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initialValue = emptyList())

    val uiState: StateFlow<ProductDetailUiState> =
        combine(product, stockItems, movementItems) { product, stockItems, stockMovements ->
            if (product == null) {
                return@combine ProductDetailUiState.Error(Throwable("Product not found"))
            }

            ProductDetailUiState.Success(product, stockItems, stockMovements)
        }
            .catch {
                ProductDetailUiState.Error(Throwable(it))
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                ProductDetailUiState.Loading
            )

    fun newStockItem(item: StockItem) {
        viewModelScope.launch(Dispatchers.IO) {
            stockItemRepository.add(item)

            val movement = StockMovement(
                itemBatch = item.batch,
                isEntry = true,
                isLoss = false,
                date = item.entryDate,
                quantity = item.quantity,
            )

            movementRepository.add(productId = item.productId, stockMovement = movement)
        }
    }
}
