package com.pietrantuono.data.api

import com.pietrantuono.data.model.TrendingMovies
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("3/trending/movie/day?language=en-UK")
    suspend fun getTrendingMovies(@Query(PAGE) page: Int): TrendingMovies

    private companion object {
        private const val PAGE = "page"
    }
}