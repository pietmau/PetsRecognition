package com.pietrantuono.data.network.entity.reddit

import com.google.gson.annotations.SerializedName

data class NetowrkRedditResponseEntity(
    @SerializedName("data") var data: NetworkPostsEntity? = NetworkPostsEntity()
)