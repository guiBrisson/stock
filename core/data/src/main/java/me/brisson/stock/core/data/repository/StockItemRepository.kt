package me.brisson.stock.core.data.repository

import kotlinx.coroutines.flow.Flow
import me.brisson.stock.core.model.StockItem

interface StockItemRepository {
    fun loadFromProductId(productId: Int): Flow<List<StockItem>>
    fun loadByBatch(batch: String): Flow<StockItem>
    suspend fun add(stockItem: StockItem)
    suspend fun delete(batch: String): Boolean
}
