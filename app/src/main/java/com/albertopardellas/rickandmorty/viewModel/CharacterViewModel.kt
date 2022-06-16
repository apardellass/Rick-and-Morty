package com.albertopardellas.rickandmorty.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.albertopardellas.rickandmorty.model.Result
import com.albertopardellas.rickandmorty.source.CharacterSource
import kotlinx.coroutines.flow.Flow

class CharacterViewModel : ViewModel() {
    val characters: Flow<PagingData<Result>> = Pager(PagingConfig(pageSize = 6)) {
        CharacterSource()
    }.flow.cachedIn(viewModelScope)
}