package com.pietrantuono.data.network.entity.reddit

import com.google.gson.annotations.SerializedName

data class NetworkPreviewEntity(
    @SerializedName("images") val images: List<NetworkImagesEntity> = emptyList()
)