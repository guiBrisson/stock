package me.brisson.stock.core.local_storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.brisson.stock.core.local_storage.entity.Product
import me.brisson.stock.core.local_storage.entity.StockItem

@Dao
interface StockItemDao {
    @Query("SELECT * FROM stock_item WHERE product_id = :productId")
    fun loadStockItemFromProductId(productId: Int): Flow<List<StockItem>>

    @Query("SELECT * FROM stock_item WHERE batch = :stockItemBatch")
    fun loadStockItemByBatch(stockItemBatch: String): Flow<StockItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg stockItem: StockItem)

    @Delete
    suspend fun delete(stockItem: StockItem)
}
