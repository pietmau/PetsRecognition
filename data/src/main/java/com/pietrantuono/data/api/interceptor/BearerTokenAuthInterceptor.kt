package com.pietrantuono.data.api.interceptor

import com.pietrantuono.data.api.api.accesstoken.RetrofitAccessTokenApiClient
import com.pietrantuono.data.api.tokenmanager.TokenManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class BearerTokenAuthInterceptor constructor(
    private val host: String = "oauth.reddit.com",
    private val tokenManager: TokenManager,
    private val accessTokenApiClient: RetrofitAccessTokenApiClient
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (request.url.host != host) {
            return chain.proceed(request)
        }
        val token = getTokenFromStore() ?: return getNewTokenAndDoRequest(request, chain)
        val response = doRequest(request, token, chain)
        return if (response.code == FORBIDDEN) {
            getNewTokenAndDoRequest(request, chain)
        } else {
            response
        }
    }

    private fun getNewTokenAndDoRequest(request: Request, chain: Interceptor.Chain): Response {
        val token = getNewToken() ?: return chain.proceed(request)
        return doRequest(request, token, chain)
    }

    private fun doRequest(request: Request, token: String, chain: Interceptor.Chain): Response {
        val newRequest = request.newBuilder().header(AUTHORIZATION, "$BEARER $token").build()
        return chain.proceed(newRequest)
    }

    private fun getTokenFromStore() = tokenManager.getToken()

    private fun getNewToken() =
        try {
            val token = accessTokenApiClient.getAccessToken(tokenManager.getDeviceId())
            token?.accessToken?.also { tokenManager.setToken(it) }
        } catch (e: Exception) {
            null // TODO handle error
        }

    private companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val BEARER = "Bearer"
        private const val FORBIDDEN = 403
    }
}
