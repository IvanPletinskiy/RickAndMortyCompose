package com.handen.characters.data.remote

import com.handen.characters.data.remote.responses.GetCharactersResponse
import retrofit2.http.GET

interface RemoteApi {
    @GET("api/character")
    suspend fun getCharacters(): GetCharactersResponse
}