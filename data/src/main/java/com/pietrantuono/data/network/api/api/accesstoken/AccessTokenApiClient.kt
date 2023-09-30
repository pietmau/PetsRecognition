package com.pietrantuono.data.network.api.api.accesstoken

import com.pietrantuono.data.network.entity.accesstoken.AccessToken

interface AccessTokenApiClient {

    fun getAccessToken(deviceId: String): AccessToken?
}