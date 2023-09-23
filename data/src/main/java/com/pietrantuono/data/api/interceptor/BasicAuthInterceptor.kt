package com.pietrantuono.data.api.interceptor

import android.util.Base64
import com.pietrantuono.mylibrary.BuildConfig
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor constructor(
    private val host: String = "www.reddit.com",
    private val credentials: String = "${BuildConfig.CLIENT_ID}:"
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (request.url.host != host) {
            return chain.proceed(request)
        }
        val encodedCredentials = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
        val newRequest = request.newBuilder().header("Authorization", "Basic $encodedCredentials").build()
        return chain.proceed(newRequest)
    }
}
