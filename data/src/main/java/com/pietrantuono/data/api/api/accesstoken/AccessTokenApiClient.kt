package com.pietrantuono.data.api.api.accesstoken

import com.pietrantuono.data.model.accesstoken.AccessToken

interface AccessTokenApiClient {

    fun getAccessToken(deviceId: String): AccessToken
}