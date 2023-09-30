package com.pietrantuono.data.network.api.api.reddit

import com.pietrantuono.data.network.entity.reddit.NetowrkRedditResponseEntity

interface RedditApiClient {

    suspend fun getSubReddit(
        subReddit: String,
        limit: Int? = null,
        before: String? = null,
        after: String? = null,
        query: String? = null
    ): NetowrkRedditResponseEntity
}