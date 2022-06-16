package com.albertopardellas.rickandmorty.model

data class Result(
    val id: Long? = null,
    val name: String? = null,
    val status: Status? = null,
    val species: Species? = null,
    val type: String? = null,
    val gender: Gender? = null,
    val origin: Location? = null,
    val location: Location? = null,
    val image: String? = null,
    val episode: List<String>? = null,
    val url: String? = null,
    val created: String? = null
)