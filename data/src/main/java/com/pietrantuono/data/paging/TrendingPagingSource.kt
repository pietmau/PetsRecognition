package com.pietrantuono.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pietrantuono.data.api.ApiClient
import com.pietrantuono.data.model.Movie
import javax.inject.Inject

class TrendingPagingSource @Inject constructor(val apiClient: ApiClient) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> =
        try {
            val pageNumber = params.key ?: 1
            val trendingMovies = apiClient.getTrendingMovies(pageNumber)
            val movies = trendingMovies.movies
            val nextPage: Int? = trendingMovies.page?.let { it + 1 }
            val prevKey = trendingMovies.page?.let { it - 1 }
            LoadResult.Page(
                data = movies,
                prevKey = prevKey,
                nextKey = nextPage
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }
}
