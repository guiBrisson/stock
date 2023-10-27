package me.brisson.stock.core.model

enum class MeasurementUnit(val baseUnitName: String, val abbreviation: String,) {
    MASS(baseUnitName = "kilogram", abbreviation = "Kg"),
    VOLUME(baseUnitName = "litre", abbreviation = "L"),
    UNIT(baseUnitName = "unit", abbreviation = "Un"),
    UNKNOWN(baseUnitName = "", abbreviation = "");

    companion object {
        fun fromString(value: String): MeasurementUnit {
            return enumValues<MeasurementUnit>().find { it.baseUnitName == value } ?: UNKNOWN
        }
    }
}