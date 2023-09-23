package com.pietrantuono.data.api

import android.util.Base64
import com.pietrantuono.data.api.interceptor.BasicAuthInterceptor
import com.pietrantuono.data.model.AccessToken
import com.pietrantuono.mylibrary.BuildConfig
import javax.inject.Inject
import okhttp3.Authenticator
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRedditApiClient @Inject constructor(authenticator: Authenticator) : RedditApiClient {

    private var api = Retrofit.Builder()// TODO reuse!!!
        .baseUrl(BASE_URL)
        .client(
            Builder()
                .addInterceptor(BasicAuthInterceptor())
                .authenticator(authenticator)
                .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RedditApi::class.java)

    override suspend fun getAccessToken(deviceId: String): AccessToken {
        val s = "${BuildConfig.CLIENT_ID}:"
        val clientId = Base64.encodeToString(s.toByteArray(), Base64.NO_WRAP)
        return api.getAccessToken(
            grantType = GRANT_TYPE,
            redirectUri = REDIRECT_URI,
            deviceId = deviceId
        )
    }

    private companion object {
        private const val BASE_URL = "https://www.reddit.com" // TODO move to gradle
        private const val GRANT_TYPE = "https://oauth.reddit.com/grants/installed_client"
        private const val REDIRECT_URI = "https://www.reddit.com"
    }
}