package com.handen.characters.data.local

import com.handen.characters.domain.entities.Character
import kotlinx.coroutines.flow.Flow

interface LocalDataStore {
    fun getCharacters(): Flow<List<Character>>
    suspend fun saveCharacters(characters: List<Character>)
}