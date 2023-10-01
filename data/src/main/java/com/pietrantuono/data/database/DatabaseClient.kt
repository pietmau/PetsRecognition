package com.pietrantuono.data.database

import com.pietrantuono.domain.model.reddit.Post

interface DatabaseClient {
    suspend fun insertPosts(posts: List<Post>, key: String?)

    suspend fun getLatestPosts(): List<Post>

    suspend fun getPostsAfter(index: Long, limit: Int): List<Post>
}