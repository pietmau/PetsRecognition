package com.pietrantuono.domain

import com.pietrantuono.domain.model.reddit.Post

interface RedditRepository {

    val defaultPageSize: Int  // TODO make dynamic
        get() = 30

    suspend fun getLatestPosts(): List<Post>

    suspend fun getNextPosts(index: Long, page: String, limit: Int): List<Post>
}