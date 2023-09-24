package com.pietrantuono.data.network.model.reddit

import com.google.gson.annotations.SerializedName

data class NetworkPosts(
    @SerializedName("after") val after: String? = null,
    @SerializedName("children") val posts: List<NetworkPost> = emptyList(),
    @SerializedName("before") val before: String? = null
)