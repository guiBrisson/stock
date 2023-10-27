package me.brisson.stock.core.local_storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    @ColumnInfo(name = "measure_unit") val measureUnit: String,
    @ColumnInfo(name = "exp_date_notification") val expirationDateNotification: Date?,
    val observation: String?,
)
