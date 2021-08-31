package com.handen.database.di

import android.content.Context
import androidx.room.Room
import com.handen.database.AppDatabase
import com.handen.database.dao.CharactersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharactersDao(database: AppDatabase): CharactersDao {
        return database.charactersDao
    }
}