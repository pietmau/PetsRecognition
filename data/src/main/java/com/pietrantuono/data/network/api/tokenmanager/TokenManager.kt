package com.pietrantuono.data.network.api.tokenmanager

interface TokenManager {

    fun getToken(): String?

    fun setToken(token: String)

    fun getDeviceId(): String

}