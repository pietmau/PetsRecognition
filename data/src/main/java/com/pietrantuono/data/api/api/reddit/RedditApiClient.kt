package com.pietrantuono.data.api.api.reddit

import com.pietrantuono.data.model.reddit.RedditResponse

interface RedditApiClient {

    suspend fun getSubReddit(
        subReddit: String,
        limit: Int?,
        before: String?,
        after: String?,
        query: String?
    ): RedditResponse
}