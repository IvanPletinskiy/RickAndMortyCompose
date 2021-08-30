package com.handen.characters.data.local.mappers

import com.handen.characters.domain.entities.Character
import com.handen.database.model.DataCharacter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersMapper @Inject constructor() {

    fun mapCharacters(list: List<Character>) = list.map {
        map(it)
    }

    fun mapDataCharacters(list: List<DataCharacter>) = list.map {
        map(it)
    }

    private fun map(dataCharacter: DataCharacter) = Character(
        dataCharacter.id,
        dataCharacter.name,
        dataCharacter.species,
        dataCharacter.status,
        dataCharacter.gender,
        dataCharacter.imageUrl,
    )

    private fun map(character: Character) = DataCharacter(
        character.id,
        character.name,
        character.species,
        character.gender,
        character.status,
        character.imageUrl
    )
}