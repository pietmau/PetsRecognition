package com.pietrantuono.data.api.authenticator

import com.pietrantuono.data.api.api.accesstoken.RetrofitAccessTokenApiClient
import com.pietrantuono.data.api.tokenmanager.TokenManager
import javax.inject.Inject
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class RedditAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val accessTokenApiClient: RetrofitAccessTokenApiClient
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        TODO("Not yet implemented")
    }
}