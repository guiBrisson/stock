package me.brisson.stock.core.data.repository

import kotlinx.coroutines.flow.Flow
import me.brisson.stock.core.model.StockItem

interface StockItemRepository {
    fun loadFromProductId(productId: Int): Flow<List<StockItem>>
    fun loadById(stockItemId: Int): Flow<StockItem>
    suspend fun add(stockItem: StockItem): Boolean
    suspend fun edit(stockItem: StockItem): Boolean
    suspend fun delete(stockItemId: Int): Boolean
}
