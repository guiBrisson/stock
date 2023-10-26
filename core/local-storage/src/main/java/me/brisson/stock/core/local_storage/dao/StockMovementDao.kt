package me.brisson.stock.core.local_storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.brisson.stock.core.local_storage.entity.StockMovementEntity

@Dao
interface StockMovementDao {
    @Query("SELECT * FROM stock_movement WHERE product_id = :productId")
    fun loadStockMovementFromProductId(productId: Int): Flow<List<StockMovementEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg stockMovement: StockMovementEntity)

    @Delete
    suspend fun delete(stockMovement: StockMovementEntity): Int


    @Query("DELETE FROM stock_movement WHERE id = :stockMovementId")
    suspend fun delete(stockMovementId: Int): Int
}
