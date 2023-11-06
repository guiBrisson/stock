package me.brisson.stock.core.local_storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "stock_movement")
data class StockMovementEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "product_id") val productId: Int,
    @ColumnInfo(name = "stock_item_batch") val stockItemBatch: String,
    @ColumnInfo(name = "is_entry") val isEntry: Boolean,
    @ColumnInfo(name = "is_loss") val isLoss: Boolean,
    val date: Date,
    @ColumnInfo(name = "expiration_date") val expirationDate: Date,
    val quantity: Int,
)
