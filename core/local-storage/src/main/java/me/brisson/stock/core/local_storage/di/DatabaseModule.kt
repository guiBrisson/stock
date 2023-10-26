package me.brisson.stock.core.local_storage.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.brisson.stock.core.local_storage.util.AppDatabase
import me.brisson.stock.core.local_storage.util.DateConverter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
            .addTypeConverter(DateConverter::class)
            .build()
    }
}
