package com.albertopardellas.rickandmorty.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.albertopardellas.rickandmorty.api.RetrofitClient
import com.albertopardellas.rickandmorty.model.Result
import retrofit2.HttpException
import java.io.IOException

class CharacterSource : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val nextPage = params.key ?: 1
            val characterList = RetrofitClient.apiService.getCharacterList(page = nextPage)
            LoadResult.Page(
                data = characterList.results!!,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (characterList.results.isEmpty()) null else nextPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}