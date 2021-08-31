package com.handen.characters.data.remote

import com.handen.characters.domain.entities.Character

interface RemoteDataService {
    suspend fun getCharacters(): List<Character>
}