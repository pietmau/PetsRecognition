package com.pietrantuono.data.api

import com.pietrantuono.data.model.TrendingMovies

interface ApiClient {

    suspend fun getTrendingMovies(page: Int): TrendingMovies
}
