package com.handen.characters.data.usecases

import com.handen.characters.data.local.LocalDataStore
import com.handen.characters.data.remote.RemoteDataService
import com.handen.characters.domain.entities.Character
import com.handen.characters.domain.usecases.GetCharactersUseCase
import com.handen.characters.util.concurrent.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCharactersUseCaseImpl @Inject constructor(
    private val remoteDataService: RemoteDataService,
    private val localDataStore: LocalDataStore,
    private val dispatcherProvider: DispatcherProvider
) : GetCharactersUseCase {
    override suspend fun invoke(): Flow<List<Character>> {
        withContext(dispatcherProvider.io) {
            remoteDataService.getCharacters().also {
                localDataStore.saveCharacters(it)
            }
        }
        return localDataStore.getCharacters()
    }
}