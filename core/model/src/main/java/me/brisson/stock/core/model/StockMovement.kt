package me.brisson.stock.core.model

import java.util.Date

data class StockMovement(
    val id: Int,
    val productId: Int,
    val itemBatch: String,
    val isEntry: Boolean,
    val isLoss: Boolean,
    val date: Date,
    val quantity: Int,
) {
    constructor(
        itemBatch: String,
        productId: Int,
        isEntry: Boolean,
        isLoss: Boolean,
        date: Date,
        quantity: Int,
    ): this(0, productId, itemBatch, isEntry, isLoss, date, quantity)

    companion object {
        fun mockForPreview(isEntry: Boolean = true) = StockMovement(
            itemBatch = "LT123",
            productId = 0,
            isEntry = isEntry,
            isLoss = false,
            date = Date(),
            quantity = 12,
        )
    }
}
