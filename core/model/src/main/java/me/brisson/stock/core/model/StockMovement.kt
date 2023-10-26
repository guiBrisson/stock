package me.brisson.stock.core.model

import java.util.Date

data class StockMovement(
    val id: Int,
    val itemBatch: String,
    val isEntry: Boolean,
    val isLoss: Boolean,
    val date: Date,
    val quantity: Int
)
