package me.brisson.stock.core.model

enum class MeasurementUnit(val baseUnitName: String,) {
    MASS(baseUnitName = "kilogram"),
    VOLUME(baseUnitName = "litre"),
    UNIT(baseUnitName = "unit"),
}