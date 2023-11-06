package me.brisson.stock.core.model

import java.util.Date

data class StockItem(
    val id: Int,
    val batch: String,
    val productId: Int,
    val entryDate: Date,
    val expirationDate: Date,
    val quantity: Int,
) {
    constructor (
        batch: String,
        productId: Int,
        entryDate: Date,
        expirationDate: Date,
        quantity: Int,
    ) : this(0, batch, productId, entryDate, expirationDate, quantity)

    companion object {
        fun mockForPreview() = StockItem(
            batch = "LT123456",
            productId = 1,
            entryDate = Date(),
            expirationDate = Date(),
            quantity = 12,
        )
    }
}
