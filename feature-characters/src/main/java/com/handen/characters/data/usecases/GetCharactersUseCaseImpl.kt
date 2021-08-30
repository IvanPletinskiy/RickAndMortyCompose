package com.handen.characters.data.usecases

import com.handen.characters.data.remote.RemoteDataService
import com.handen.characters.domain.entities.Character
import com.handen.characters.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharactersUseCaseImpl @Inject constructor(
    private val remoteDataService: RemoteDataService
) : GetCharactersUseCase {
    override fun invoke(): Flow<List<Character>> {
        return flow {
            emit(remoteDataService.getCharacters())
        }
    }
}