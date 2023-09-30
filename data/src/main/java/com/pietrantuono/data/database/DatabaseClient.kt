package com.pietrantuono.data.database

import com.pietrantuono.domain.model.reddit.Post

interface DatabaseClient {
    suspend fun insertPosts(posts: List<Post>)

}