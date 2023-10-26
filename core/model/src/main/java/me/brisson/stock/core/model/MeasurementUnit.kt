package me.brisson.stock.core.model

enum class MeasurementUnit(val baseUnitName: String) {
    MASS(baseUnitName = "kilogram"),
    VOLUME(baseUnitName = "litre"),
    UNIT(baseUnitName = "unit"),
    UNKNOWN(baseUnitName = "");

    companion object {
        fun fromString(value: String): MeasurementUnit {
            return enumValues<MeasurementUnit>().find { it.baseUnitName == value } ?: UNKNOWN
        }
    }
}