package com.pietrantuono.domain

import com.pietrantuono.domain.model.reddit.Post

interface RedditRepository {

    suspend fun getLatestPosts(): List<Post>
}