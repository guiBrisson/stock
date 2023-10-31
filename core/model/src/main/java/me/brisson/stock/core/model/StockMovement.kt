package me.brisson.stock.core.model

import java.util.Date

data class StockMovement(
    val id: Int,
    val itemBatch: String,
    val isEntry: Boolean,
    val isLoss: Boolean,
    val date: Date,
    val quantity: Int,
) {
    constructor(
        itemBatch: String,
        isEntry: Boolean,
        isLoss: Boolean,
        date: Date,
        quantity: Int,
    ): this(0, itemBatch, isEntry, isLoss, date, quantity)
}
