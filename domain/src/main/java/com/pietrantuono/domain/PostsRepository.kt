package com.pietrantuono.domain

import com.pietrantuono.domain.model.reddit.Post

interface PostsRepository {

    val defaultPageSize: Int  // TODO make dynamic
        get() = 30

    suspend fun getLatestPosts(): List<Post>

    suspend fun getNextPosts(date: Long, page: String, limit: Int): List<Post>
}