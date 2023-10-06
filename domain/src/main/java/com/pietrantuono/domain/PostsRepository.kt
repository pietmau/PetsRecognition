package com.pietrantuono.domain

import com.pietrantuono.domain.model.reddit.Post

interface PostsRepository {

    val defaultPageSize: Int  // TODO make dynamic
        get() = DEFAULT_PAGE_SIZE

    suspend fun getLatestPosts(): List<Post>

    suspend fun getNextPosts(date: Long, page: String, limit: Int): List<Post>

    private companion object {
        private const val DEFAULT_PAGE_SIZE = 30
    }
}