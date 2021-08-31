package com.handen.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.handen.database.model.DataCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Insert
    suspend fun saveCharacters(characters: List<DataCharacter>)

    @Query("SELECT * FROM characters")
    fun getCharacters(): Flow<List<DataCharacter>>
}