package com.pietrantuono.data.model.reddit

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("source") var source: Source? = Source(),
    @SerializedName("resolutions") var resolutions: List<Resolutions> = emptyList(),
    @SerializedName("id") var id: String? = null
)

data class Source(
    @SerializedName("url") var url: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null
)

data class Resolutions(
    @SerializedName("url") var url: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null
)