package com.pietrantuono.data.network.api.api.reddit

import com.pietrantuono.data.network.model.reddit.NetowrkRedditResponse

interface RedditApiClient {

    suspend fun getSubReddit(
        subReddit: String,
        limit: Int?,
        before: String?,
        after: String?,
        query: String?
    ): NetowrkRedditResponse
}