package com.handen.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.handen.database.dao.CharactersDao
import com.handen.database.model.DataCharacter

@Database(entities = [DataCharacter::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val charactersDao: CharactersDao
}