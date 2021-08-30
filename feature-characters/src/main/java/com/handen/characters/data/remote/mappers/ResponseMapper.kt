package com.handen.characters.data.remote.mappers

import com.handen.characters.data.remote.responses.CharacterResponse
import com.handen.characters.data.remote.responses.GetCharactersResponse
import com.handen.characters.domain.entities.Character
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResponseMapper @Inject constructor(){
    fun map(response: GetCharactersResponse): List<Character> {
        return response.results.map {
            map(it)
        }
    }

    private fun map(response: CharacterResponse) =
        Character(
            response.id,
            response.name,
            response.species,
            response.status,
            response.gender,
            response.image.orEmpty()
        )
}