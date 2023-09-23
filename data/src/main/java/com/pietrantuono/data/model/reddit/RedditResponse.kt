package com.pietrantuono.data.model.reddit

import com.google.gson.annotations.SerializedName

data class RedditResponse(
    @SerializedName("kind") var kind: String? = null,
    @SerializedName("data") var data: Content? = Content()
)