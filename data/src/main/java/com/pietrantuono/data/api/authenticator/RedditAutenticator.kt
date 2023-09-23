package com.pietrantuono.data.api.authenticator

import com.pietrantuono.data.api.tokenmanager.TokenManager
import javax.inject.Inject
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class RedditAuthenticator @Inject constructor(val tokenManager: TokenManager) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        TODO("Not yet implemented")
    }
}