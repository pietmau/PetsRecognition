package com.pietrantuono.domain.model.reddit

data class Post(
    val key: Long = 0,
    val kind: String? = null,
    val name: String,
    val subreddit: String? = null,
    val thumbnail: String? = null,
    val title: String? = null,
    val ups: Int? = null,
    val created: Long? = null,
    val images: List<Image> = emptyList(),
    val subredditId: String? = null,
    val id: String? = null,
    val author: String? = null,
    val numComments: Int? = null,
    val permalink: String? = null,
    val url: String? = null,
    val score: Int? = null,
    val before: String? = null,
    val after: String? = null,
    val page: String? = null,
)