package me.brisson.stock.core.local_storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.brisson.stock.core.local_storage.entity.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE id LIKE :productId")
    fun loadById(productId: Int): Flow<ProductEntity?>

    @Query("SELECT * FROM product WHERE name LIKE :name")
    fun findByName(name: String): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg product: ProductEntity)

    @Delete
    suspend fun delete(product: ProductEntity)
}
