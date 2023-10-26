package me.brisson.stock.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.brisson.stock.core.data.mapper.asEntity
import me.brisson.stock.core.data.mapper.asModel
import me.brisson.stock.core.local_storage.dao.StockMovementDao
import me.brisson.stock.core.model.StockMovement
import javax.inject.Inject

class StockMovementRepositoryImpl @Inject constructor(
    private val movementDao: StockMovementDao,
): StockMovementRepository {
    override fun loadFromProductId(productId: Int): Flow<List<StockMovement>> {
        return movementDao.loadStockMovementFromProductId(productId).map { it.asModel() }
    }

    override suspend fun add(productId: Int, stockMovement: StockMovement) {
        movementDao.insertAll(stockMovement.asEntity(productId))
    }

    override suspend fun delete(stockMovement: StockMovement): Boolean {
        return movementDao.delete(stockMovement.id) == 1
    }
}