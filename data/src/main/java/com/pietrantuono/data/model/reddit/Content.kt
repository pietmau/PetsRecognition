package com.pietrantuono.data.model.reddit

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("after") var after: String? = null,
    @SerializedName("dist") var dist: Int? = null,
    @SerializedName("modhash") var modhash: String? = null,
    @SerializedName("geo_filter") var geoFilter: String? = null,
    @SerializedName("children") var children: List<Children> = emptyList(),
    @SerializedName("before") var before: String? = null
)