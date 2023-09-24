package com.pietrantuono.data.network.model.reddit

import com.google.gson.annotations.SerializedName
import com.pietrantuono.domain.model.reddit.RedditResponse

data class NetowrkRedditResponse(
    @SerializedName("data") var data: NetworkContent? = NetworkContent()
)