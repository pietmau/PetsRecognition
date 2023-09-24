package com.pietrantuono.domain.model.reddit

data class Data(
    val subreddit: String? = null,
    val thumbnail: String? = null,
    val title: String? = null,
    val ups: Int? = null,
    val created: Int? = null,
    val preview: Preview? = null,
    val subredditId: String? = null,
    val id: String? = null,
    val author: String? = null,
    val numComments: Int? = null,
    val permalink: String? = null,
    val url: String? = null
)