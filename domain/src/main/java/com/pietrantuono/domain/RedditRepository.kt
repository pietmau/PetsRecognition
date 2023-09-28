package com.pietrantuono.domain

import com.pietrantuono.domain.model.reddit.Posts

interface RedditRepository {

    suspend fun getPosts(before: String? = null, after: String? = null, limit: Int? = null): Posts


}