package com.handen.characters.domain.usecases

import com.handen.characters.domain.entities.Character
import kotlinx.coroutines.flow.Flow

fun interface GetCharactersUseCase {
    suspend operator fun invoke(): Flow<List<Character>>
}