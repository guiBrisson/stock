package me.brisson.stock.core.local_storage.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.brisson.stock.core.local_storage.util.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providesProductDao(appDatabase: AppDatabase) = appDatabase.productDao()

    @Provides
    fun providesStockItemDao(appDatabase: AppDatabase) = appDatabase.stockItemDao()

    @Provides
    fun providesStockMovementDao(appDatabase: AppDatabase) = appDatabase.stockMovementDao()

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
            .build()
    }
}
