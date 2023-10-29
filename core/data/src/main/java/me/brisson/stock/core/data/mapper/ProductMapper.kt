package me.brisson.stock.core.data.mapper

import me.brisson.stock.core.local_storage.entity.ProductEntity
import me.brisson.stock.core.model.MeasurementUnit
import me.brisson.stock.core.model.Product

fun Product.asEntity() = ProductEntity(
    id = id,
    name = name,
    measureUnit = measurementUnit.baseUnitName,
    expirationDayNotification = expirationDay,
    observation = observation,
    total = total,
)

fun List<ProductEntity>.asModel() = this.map { it.asModel() }

fun ProductEntity.asModel() = Product(
    id = id,
    name = name,
    measurementUnit = MeasurementUnit.fromString(measureUnit),
    expirationDay = expirationDayNotification,
    observation = observation,
    total = total,
)