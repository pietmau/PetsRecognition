package com.pietrantuono.data.model

import com.google.gson.annotations.SerializedName

data class TrendingMovies(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: List<Results> = listOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)