package com.handen.characters.domain.usecases

import com.handen.characters.domain.entities.Character
import kotlinx.coroutines.flow.Flow

fun interface GetCharactersUseCase {
    operator fun invoke(): Flow<List<Character>>
}