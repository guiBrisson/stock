package me.brisson.stock.core.local_storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.brisson.stock.core.local_storage.entity.StockItemEntity

@Dao
interface StockItemDao {
    @Query("SELECT * FROM stock_item WHERE product_id = :productId")
    fun loadStockItemFromProductId(productId: Int): Flow<List<StockItemEntity>>

    @Query("SELECT * FROM stock_item WHERE batch = :stockItemBatch")
    fun loadStockItemByBatch(stockItemBatch: String): Flow<StockItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg stockItem: StockItemEntity)

    @Delete
    suspend fun delete(stockItem: StockItemEntity)
}
