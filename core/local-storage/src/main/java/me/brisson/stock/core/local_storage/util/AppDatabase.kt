package me.brisson.stock.core.local_storage.util

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.brisson.stock.core.local_storage.dao.ProductDao
import me.brisson.stock.core.local_storage.dao.StockItemDao
import me.brisson.stock.core.local_storage.dao.StockMovementDao
import me.brisson.stock.core.local_storage.entity.Product
import me.brisson.stock.core.local_storage.entity.StockItem
import me.brisson.stock.core.local_storage.entity.StockMovement

@Database(entities = [Product::class, StockItem::class, StockMovement::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun stockItemDao(): StockItemDao
    abstract fun stockMovementDao(): StockMovementDao
}
