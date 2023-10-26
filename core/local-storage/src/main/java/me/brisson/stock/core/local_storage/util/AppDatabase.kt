package me.brisson.stock.core.local_storage.util

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.brisson.stock.core.local_storage.dao.ProductDao
import me.brisson.stock.core.local_storage.entity.Product

@Database(entities = [Product::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}
