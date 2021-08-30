package com.handen.characters.data.remote.responses

data class GetCharactersResponse(
    val info: InfoResponse,
    val results: List<CharacterResponse>
)