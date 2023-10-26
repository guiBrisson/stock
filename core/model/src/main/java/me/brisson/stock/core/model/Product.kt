package me.brisson.stock.core.model

import java.util.Date

data class Product(
    val id: Int,
    val name: String,
    val measurementUnit: MeasurementUnit,
    val expirationDate: Date?,
    val observation: String?,
)
