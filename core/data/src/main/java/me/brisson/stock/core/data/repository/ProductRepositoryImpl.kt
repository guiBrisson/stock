package me.brisson.stock.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.brisson.stock.core.data.mapper.asEntity
import me.brisson.stock.core.data.mapper.asModel
import me.brisson.stock.core.local_storage.dao.ProductDao
import me.brisson.stock.core.model.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
) : ProductRepository {
    override fun getAll(): Flow<List<Product>> {
        return productDao.getAll().map { it.asModel() }
    }

    override fun loadById(productId: Int): Flow<Product?> {
        return productDao.loadById(productId).map { it?.asModel() }
    }

    override fun findByName(name: String): Flow<List<Product>> {
        return productDao.findByName(name).map { it.asModel() }
    }

    override suspend fun add(product: Product) {
        productDao.insertAll(product.asEntity())
    }

    override suspend fun delete(product: Product): Boolean {
        return productDao.delete(product.asEntity()) == 1
    }
}
