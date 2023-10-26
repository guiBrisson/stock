package me.brisson.stock.core.local_storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "stock_item")
data class StockItemEntity(
    @PrimaryKey(autoGenerate = true) val batch: String,
    @ColumnInfo(name = "product_id") val productId: Int,
    @ColumnInfo(name = "entry_date") val entryDate: Date,
    @ColumnInfo(name = "expiration_date") val expirationDate: Date,
    val price: Float,
    val quantity: Int,
)
