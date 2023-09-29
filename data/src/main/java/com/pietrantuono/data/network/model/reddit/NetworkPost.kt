package com.pietrantuono.data.network.model.reddit

import com.google.gson.annotations.SerializedName

data class NetworkPost(
    @SerializedName("kind") val kind: String? = null,
    @SerializedName("data") val data: NetworkData? = null
)