package com.albertopardellas.rickandmorty.model

data class LocationDetail(
    val id: Long? = null,
    val name: String? = null,
    val type: String? = null,
    val dimension: String? = null,
    val residents: List<String>? = null,
    val url: String? = null,
    val created: String? = null
)
