package com.pietrantuono.data.network.model.reddit

import com.google.gson.annotations.SerializedName

data class NetowrkRedditResponse(
    @SerializedName("data") var data: NetworkPosts? = NetworkPosts()
)