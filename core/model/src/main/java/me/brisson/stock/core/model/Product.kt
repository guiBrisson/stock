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

    companion object {
        fun mockForPreview() = Product(
            name = "Arroz branco",
            measurementUnit = MeasurementUnit.MASS,
            expirationDay = 40,
            observation = "ex. Guardar o arroz branco em garráfas pet seladas a vácuo."
        )
    }
}

