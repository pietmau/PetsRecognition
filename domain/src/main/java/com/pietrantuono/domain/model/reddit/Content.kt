package com.pietrantuono.domain.model.reddit

data class Content(
    val after: String? = null,
    val posts: List<Post> = emptyList(),
    val before: String? = null
)