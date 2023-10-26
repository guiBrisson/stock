package me.brisson.stock.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.brisson.stock.core.data.repository.StockItemRepository
import me.brisson.stock.core.data.repository.StockItemRepositoryImpl
import me.brisson.stock.core.data.repository.StockMovementRepository
import me.brisson.stock.core.data.repository.StockMovementRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsStockMovementRepository(
        stockMovementRepositoryImpl: StockMovementRepositoryImpl,
    ): StockMovementRepository

    @Binds
    fun bindsStockItemRepository(
        stockItemRepositoryImpl: StockItemRepositoryImpl,
    ): StockItemRepository

}
