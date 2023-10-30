package me.brisson.stock.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.brisson.stock.core.data.mapper.asEntity
import me.brisson.stock.core.data.mapper.asModel
import me.brisson.stock.core.local_storage.dao.ProductDao
import me.brisson.stock.core.local_storage.dao.StockItemDao
import me.brisson.stock.core.model.StockItem
import javax.inject.Inject

class StockItemRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val stockItemDao: StockItemDao,
) : StockItemRepository {
    override fun loadFromProductId(productId: Int): Flow<List<StockItem>> {
        return stockItemDao.loadStockItemFromProductId(productId).map { it.asModel() }
    }

    override fun loadById(stockItemId: Int): Flow<StockItem> {
        return stockItemDao.loadStockItemById(stockItemId).map { it.asModel() }
    }

    override suspend fun add(stockItem: StockItem): Boolean {
        return stockItemDao.insertAll(stockItem.asEntity()).isNotEmpty()
    }

    override suspend fun delete(stockItemId: Int): Boolean {
        return stockItemDao.delete(stockItemId) == 1
    }

    // Todo: Adjust product total amount
    private suspend fun updateProductTotalAmount(productId: Int, amount: Int) {
        productDao.loadById(productId).collect { productEntity ->
            val currentProductAmount = productEntity?.total ?: 0
            var newAmount = currentProductAmount + amount
            if (newAmount < 0) newAmount = 0 // Only positive number should be accepted

            productEntity?.copy(total = newAmount)?.let { updatedProduct ->
                productDao.insertAll(updatedProduct)
            }
        }
    }
}
