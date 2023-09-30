package com.pietrantuono.data.database

import com.pietrantuono.domain.model.reddit.Post

interface DatabaseClient {
    suspend fun insertPosts(posts: List<Post>, key: String?)

    suspend fun getPosts(key: String?, limit: Int = DEFAULT_LIMIT): List<Post>

    private companion object {
        const val DEFAULT_LIMIT = 10
    }
}