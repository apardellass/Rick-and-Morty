package com.albertopardellas.rickandmorty.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.albertopardellas.rickandmorty.model.LocationDetail
import com.albertopardellas.rickandmorty.model.Result
import com.albertopardellas.rickandmorty.source.CharacterSource
import com.albertopardellas.rickandmorty.source.LocationDetailSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {
    val characters: Flow<PagingData<Result>> = Pager(PagingConfig(pageSize = 6)) {
        CharacterSource()
    }.flow.cachedIn(viewModelScope)

    var locationDetail: LocationDetail by mutableStateOf(LocationDetail())
    private var errorMessage: String by mutableStateOf("")
    var selectedCharacter: Result? = null

    fun getLocationDetails(url: String) {
        viewModelScope.launch {
            try {
                locationDetail = LocationDetailSource().getLocationDetails(url)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}