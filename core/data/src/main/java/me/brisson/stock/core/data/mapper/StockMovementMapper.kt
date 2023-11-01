package me.brisson.stock.core.data.mapper

import me.brisson.stock.core.local_storage.entity.StockMovementEntity
import me.brisson.stock.core.model.StockMovement


fun StockMovement.asEntity(productId: Int) = StockMovementEntity(
    id = id,
    productId = productId,
    stockItemBatch =  itemBatch,
    isEntry = isEntry,
    isLoss = isLoss,
    date = date,
    quantity = quantity,
)

fun List<StockMovementEntity>.asModel() = this.map { it.asModel() }

fun StockMovementEntity.asModel() = StockMovement(
    id = id,
    productId = productId,
    itemBatch =  stockItemBatch,
    isEntry = isEntry,
    isLoss = isLoss,
    date = date,
    quantity = quantity,
)