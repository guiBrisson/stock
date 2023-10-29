package me.brisson.stock.core.data.repository

import kotlinx.coroutines.flow.Flow
import me.brisson.stock.core.model.Product

interface ProductRepository {
    fun getAll(): Flow<List<Product>>
    fun loadById(productId: Int): Flow<Product?>
    fun findByName(name: String): Flow<List<Product>>
    suspend fun add(product: Product): Boolean
    suspend fun delete(product: Product): Boolean
}
