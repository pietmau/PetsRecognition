package com.pietrantuono.data.network.api.api.accesstoken

import com.pietrantuono.data.network.model.accesstoken.AccessToken

interface AccessTokenApiClient {

    fun getAccessToken(deviceId: String): AccessToken?
}