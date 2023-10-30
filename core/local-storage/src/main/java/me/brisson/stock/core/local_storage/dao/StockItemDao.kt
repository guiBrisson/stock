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

    @Query("SELECT * FROM stock_item WHERE id = :stockItemId")
    fun loadStockItemById(stockItemId: Int): Flow<StockItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg stockItem: StockItemEntity): List<Long>

    @Delete
    suspend fun delete(stockItem: StockItemEntity)

    @Query("DELETE FROM stock_item WHERE id = :stockItemId")
    suspend fun delete(stockItemId: Int): Int
}
