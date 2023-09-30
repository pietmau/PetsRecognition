package com.pietrantuono.domain

import com.pietrantuono.domain.model.reddit.Post

interface RedditRepository { //TODO remove

    suspend fun getPosts(before: String? = null, after: String? = null, limit: Int? = null): List<Post>


}