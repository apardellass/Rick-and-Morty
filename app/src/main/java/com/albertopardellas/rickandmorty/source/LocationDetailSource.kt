package com.albertopardellas.rickandmorty.source

import com.albertopardellas.rickandmorty.api.RetrofitClient
import com.albertopardellas.rickandmorty.model.LocationDetail

class LocationDetailSource {
    suspend fun getLocationDetails(url: String): LocationDetail {
        return RetrofitClient.apiService.getLocationDetails(url)
    }
}