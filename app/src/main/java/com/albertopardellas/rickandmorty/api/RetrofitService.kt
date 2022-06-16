package com.albertopardellas.rickandmorty.api

import com.albertopardellas.rickandmorty.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("users")
    suspend fun getCharacterList(@Query("page") page: Int): CharacterResponse
}