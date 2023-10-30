package me.brisson.stock.core.data.mapper

import me.brisson.stock.core.local_storage.entity.StockItemEntity
import me.brisson.stock.core.model.StockItem

fun StockItem.asEntity() = StockItemEntity(
    id = id,
    batch = batch,
    productId = productId,
    expirationDate = expirationDate,
    entryDate = entryDate,
    price = price,
    quantity = quantity,
)

fun List<StockItemEntity>.asModel() = this.map { it.asModel() }

fun StockItemEntity.asModel() = StockItem(
    id = id,
    batch = batch,
    productId = productId,
    entryDate = entryDate,
    expirationDate = expirationDate,
    price = price,
    quantity = quantity,
)