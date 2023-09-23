package com.pietrantuono.data.api.interceptor

import com.pietrantuono.data.api.tokenmanager.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class BearerTokenAuthInterceptor constructor(
    private val host: String = "/oauth.reddit.com",
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val token = tokenManager.getToken()
        if (request.url.host != host || token == null) {
            return chain.proceed(request)
        }
        val newRequest = request.newBuilder().header("Authorization", "Bearer $token").build()
        return chain.proceed(newRequest)
    }
}
