package com.pietrantuono.data.api

import com.pietrantuono.data.model.AccessToken

interface  AccessTokenApiClient {

    suspend fun getAccessToken(deviceId: String): AccessToken
}