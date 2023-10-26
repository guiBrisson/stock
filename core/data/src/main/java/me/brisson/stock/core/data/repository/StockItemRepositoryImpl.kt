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

    override fun loadByBatch(batch: String): Flow<StockItem> {
        return stockItemDao.loadStockItemByBatch(batch).map { it.asModel() }
    }

    override suspend fun add(productId: Int, stockItem: StockItem) {
        stockItemDao.insertAll(stockItem.asEntity(productId))
    }

    override suspend fun delete(batch: String): Boolean {
        return stockItemDao.delete(batch) == 1
    }
}
