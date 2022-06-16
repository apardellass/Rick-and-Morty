package com.albertopardellas.rickandmorty.model

data class CharacterResponse(
    val info: Info? = null,
    val results: List<Result>? = null
)