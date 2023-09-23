package com.pietrantuono.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pietrantuono.data.model.Movie
import javax.inject.Inject

class TrendingPagingSource constructor(val apiClient: Any) : PagingSource<Int, Movie>() { // TODO abstract it
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> = TODO()
//        try {
//            val pageNumber = params.key ?: 1
//            val trendingMovies = apiClient.getTrendingMovies(pageNumber)
//            val movies = trendingMovies.movies
//            val page = trendingMovies.page
//            val nextPage = page?.let { it + 1 }
//            LoadResult.Page(
//                data = movies,
//                prevKey = page,
//                nextKey = nextPage
//            )
//        } catch (exception: Exception) {
//            LoadResult.Error(exception) // TODO log
//        }

    override fun getRefreshKey(state: PagingState<Int, Movie>) =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}
