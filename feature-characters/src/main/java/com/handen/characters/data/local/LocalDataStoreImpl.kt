package com.handen.characters.data.local

import com.handen.characters.data.local.mappers.CharactersMapper
import com.handen.characters.domain.entities.Character
import com.handen.database.dao.CharactersDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataStoreImpl @Inject constructor(
    private val charactersDao: CharactersDao,
    private val mapper: CharactersMapper
) : LocalDataStore {

    override fun getCharacters(): Flow<List<Character>> {
        return charactersDao.getCharacters().map {
            mapper.mapDataCharacters(it)
        }
    }

    override suspend fun saveCharacters(characters: List<Character>) {
        charactersDao.saveCharacters(mapper.mapCharacters(characters))
    }
}