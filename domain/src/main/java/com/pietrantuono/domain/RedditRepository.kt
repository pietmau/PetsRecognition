package com.pietrantuono.domain

import com.pietrantuono.domain.model.reddit.Post

interface RedditRepository {

    suspend fun getLatestPosts(): List<Post>

    suspend fun getNextPosts(index: Long, page: String, limit: Int): List<Post>
}