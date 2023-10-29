package me.brisson.stock.core.model

import java.util.Date

data class StockItem(
    val batch: String,
    val productId: Int,
    val entryDate: Date,
    val expirationDate: Date,
    val price: Float,
    val quantity: Int,
)
