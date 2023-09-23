package com.pietrantuono.data.api.authenticator

import com.pietrantuono.data.api.api.accesstoken.RetrofitAccessTokenApiClient
import com.pietrantuono.data.api.tokenmanager.TokenManager
import javax.inject.Inject
import okhttp3.Authenticator
import okhttp3.Response
import okhttp3.Route

class RedditAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val accessTokenApiClient: RetrofitAccessTokenApiClient
) : Authenticator {
    override fun authenticate(route: Route?, response: Response) =
        try {
            val token = accessTokenApiClient.getAccessToken(tokenManager.getDeviceId())
            token.accessToken?.let {
                tokenManager.setToken(it)
                response.request.newBuilder()
                    .header("Authorization", "Bearer $it")
                    .build()
            } ?: response.request
        } catch (e: Exception) {
            response.request // TODO handle error
        }
}
