package me.brisson.stock.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.brisson.stock.core.data.mapper.asEntity
import me.brisson.stock.core.data.mapper.asModel
import me.brisson.stock.core.local_storage.dao.StockItemDao
import me.brisson.stock.core.model.StockItem
import javax.inject.Inject

class StockItemRepositoryImpl @Inject constructor(
    private val stockItemDao: StockItemDao,
) : StockItemRepository {
    override fun loadFromProductId(productId: Int): Flow<List<StockItem>> {
        return stockItemDao.loadStockItemFromProductId(productId).map { it.asModel() }
    }

    override fun loadById(stockItemId: Int): Flow<StockItem> {
        return stockItemDao.loadStockItemById(stockItemId).map { it.asModel() }
    }

    override suspend fun add(stockItem: StockItem): Boolean {
        var itemEntity = stockItem.asEntity()

        // Checking if there is the product has an item with this batch already
        // if that's true, just sums up the quantity as the total quantity
        stockItemDao.loadStockItemByBatch(stockItem.productId, stockItem.batch)?.let { dbEntity ->
            val currentQuantity = dbEntity.quantity
            val newQuantity = currentQuantity + stockItem.quantity
            itemEntity = dbEntity.copy(quantity = newQuantity)
        }

        return stockItemDao.insertAll(itemEntity).isNotEmpty()
    }

    override suspend fun edit(stockItem: StockItem): Boolean {
        // Deleting the stock item if the quantity reaches zero
        if (stockItem.quantity <= 0) {
            return stockItemDao.delete(stockItem.id) == 1
        }

        return stockItemDao.insertAll(stockItem.asEntity()).isNotEmpty()
    }

    override suspend fun delete(stockItemId: Int): Boolean {
        return stockItemDao.delete(stockItemId) == 1
    }
}
