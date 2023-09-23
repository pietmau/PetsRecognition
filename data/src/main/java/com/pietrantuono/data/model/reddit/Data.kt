package com.pietrantuono.data.model.reddit

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("subreddit") var subreddit: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("ups") var ups: Int? = null,
    @SerializedName("created") var created: Int? = null,
    @SerializedName("preview") var preview: Preview? = Preview(),
    @SerializedName("subreddit_id") var subredditId: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("author") var author: String? = null,
    @SerializedName("num_comments") var numComments: Int? = null,
    @SerializedName("permalink") var permalink: String? = null,
    @SerializedName("url") var url: String? = null,
)