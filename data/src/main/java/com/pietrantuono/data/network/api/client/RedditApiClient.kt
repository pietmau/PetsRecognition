package com.pietrantuono.data.network.api.client

import com.pietrantuono.domain.model.reddit.Post

interface RedditApiClient {

    suspend fun getSubReddit(
        subReddit: String,
        limit: Int? = null,
        before: String? = null,
        after: String? = null,
        query: String? = null,
    ): List<Post>
}