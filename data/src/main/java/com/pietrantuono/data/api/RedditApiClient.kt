package com.pietrantuono.data.api

import com.pietrantuono.data.model.AccessToken

interface  RedditApiClient {

    suspend fun getAccessToken(deviceId: String): AccessToken
}