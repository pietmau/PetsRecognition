package com.pietrantuono.data.model

import com.google.gson.annotations.SerializedName

data class TrendingMovies(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var movies: List<Movie> = emptyList(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)