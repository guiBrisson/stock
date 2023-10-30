package me.brisson.stock.core.model

import java.util.Date

data class StockItem(
    val id: Int,
    val batch: String,
    val productId: Int,
    val entryDate: Date,
    val expirationDate: Date,
    val price: Float?,
    val quantity: Int,
) {
    constructor (
        batch: String,
        productId: Int,
        entryDate: Date,
        expirationDate: Date,
        price: Float?,
        quantity: Int,
    ) : this(0, batch, productId, entryDate, expirationDate, price, quantity)
}
