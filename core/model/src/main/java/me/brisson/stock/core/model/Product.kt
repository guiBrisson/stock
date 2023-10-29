package me.brisson.stock.core.model


data class Product(
    val id: Int,
    val name: String,
    val measurementUnit: MeasurementUnit,
    val expirationDay: Int?,
    val observation: String?,
    val total: Int,
) {
    constructor(
        name: String,
        measurementUnit: MeasurementUnit,
        expirationDay: Int?,
        observation: String?
    ) : this(
        id = 0,
        name = name,
        measurementUnit = measurementUnit,
        expirationDay = expirationDay,
        observation = observation,
        total = 0,
    )
}
