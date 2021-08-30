package com.handen.characters.data.remote

import com.handen.characters.data.remote.mappers.ResponseMapper
import com.handen.characters.domain.entities.Character
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataServiceImpl @Inject constructor(
    private val remoteApi: RemoteApi,
    private val mapper: ResponseMapper
) : RemoteDataService {
    override suspend fun getCharacters(): List<Character> {
        return mapper.map(remoteApi.getCharacters())
    }
}