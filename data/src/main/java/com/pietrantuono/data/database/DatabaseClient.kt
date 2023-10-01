package com.pietrantuono.data.database

import com.pietrantuono.domain.model.reddit.Post

interface DatabaseClient {
    suspend fun insertPosts(posts: List<Post>)

    suspend fun getLatestPosts(limit: Int): List<Post>

    suspend fun getPostsAfter(index: Long, limit: Int): List<Post>
}