package me.brisson.stock.core.data.repository

import kotlinx.coroutines.flow.Flow
import me.brisson.stock.core.model.StockMovement

interface StockMovementRepository {
    fun loadFromProductId(productId: Int): Flow<List<StockMovement>>
    suspend fun add(stockMovement: StockMovement)
    suspend fun delete(stockMovement: StockMovement): Boolean
}
