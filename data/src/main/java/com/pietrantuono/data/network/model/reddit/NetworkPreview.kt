package com.pietrantuono.data.network.model.reddit

import com.google.gson.annotations.SerializedName

data class NetworkPreview(
    @SerializedName("images") val images: List<NetworkImages> = emptyList()
)