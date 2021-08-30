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
            AppDatabase::class.java, "appDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharactersDao(database: AppDatabase): CharactersDao {
        return database.charactersDao
    }
}

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class DatabaseModule {
//
//    companion object {
//        private const val DATABASE_NAME = "appDatabase"
//
////        @Provides
////        @Singleton
////        fun provideDatabase(): AppDatabase {
//////        Room.databaseBuilder(
//////            context,
//////            AppDatabase::class.java, DATABASE_NAME
//////        ).build()
////            return MyDatabase()
////        }
//
//        class MyDatabase : AppDatabase() {
//            override val charactersDao: CharactersDao
//                get() = TODO("Not yet implemented")
//
//            override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
//                TODO("Not yet implemented")
//            }
//
//            override fun createInvalidationTracker(): InvalidationTracker {
//                TODO("Not yet implemented")
//            }
//
//            override fun clearAllTables() {
//                TODO("Not yet implemented")
//            }
//        }
//    }
//
////        @Provides
////        @Singleton
////        fun provideCharactersDao(database: AppDatabase): CharactersDao {
////            return database.charactersDao
////        }
//}