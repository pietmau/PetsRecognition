package com.pietrantuono.data.network.model.reddit

import com.google.gson.annotations.SerializedName
import com.pietrantuono.domain.model.reddit.Data

data class NetworkData(
    @SerializedName("name") val name: String? = null,
    @SerializedName("score") val score: Int? = null,
    @SerializedName("subreddit") val subreddit: String? = null,
    @SerializedName("thumbnail") val thumbnail: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("ups") val ups: Int? = null,
    @SerializedName("created") val created: Int? = null,
    @SerializedName("preview") val preview: NetworkPreview? = NetworkPreview(),
    @SerializedName("subreddit_id") val subredditId: String? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("num_comments") val numComments: Int? = null,
    @SerializedName("permalink") val permalink: String? = null,
    @SerializedName("url") val url: String? = null,
)