package com.pietrantuono.data.model.reddit

import com.google.gson.annotations.SerializedName

data class Preview(
    @SerializedName("images") var images: List<Images> = emptyList(),
    @SerializedName("enabled") var enabled: Boolean? = null
)