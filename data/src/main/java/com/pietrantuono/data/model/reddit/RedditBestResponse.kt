package com.pietrantuono.data.model.reddit

import com.google.gson.annotations.SerializedName

data class RedditBestResponse(
    @SerializedName("kind") var kind: String? = null,
    @SerializedName("data") var data: Content? = Content()
)