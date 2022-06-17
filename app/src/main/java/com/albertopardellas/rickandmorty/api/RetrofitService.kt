package com.albertopardellas.rickandmorty.api

import com.albertopardellas.rickandmorty.model.CharacterResponse
import com.albertopardellas.rickandmorty.model.LocationDetail
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RetrofitService {
    @GET("character")
    suspend fun getCharacterList(@Query("page") page: Int): CharacterResponse

    @GET
    suspend fun getLocationDetails(@Url url: String): LocationDetail
}