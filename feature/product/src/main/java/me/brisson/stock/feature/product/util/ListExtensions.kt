package me.brisson.stock.feature.product.util

fun <T>makeList(model: T, size: Int): List<T> {
    val mutableList = mutableListOf<T>()

    for (index in 0 until size) {
        mutableList.add(model)
    }

    return mutableList
}
