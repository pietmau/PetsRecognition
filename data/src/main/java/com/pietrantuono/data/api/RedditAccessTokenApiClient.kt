package com.pietrantuono.data.api

import com.pietrantuono.data.model.AccessToken

interface  RedditAccessTokenApiClient {

    suspend fun getAccessToken(deviceId: String): AccessToken
}