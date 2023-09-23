package com.pietrantuono.data.api.api

import com.pietrantuono.data.api.AccessTokenApiClient
import com.pietrantuono.data.api.interceptor.BasicAuthInterceptor
import com.pietrantuono.data.model.AccessToken
import com.pietrantuono.mylibrary.BuildConfig
import javax.inject.Inject
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAccessTokenApiClient @Inject constructor() : AccessTokenApiClient {

    private var mRedditAccessTokenApi = Retrofit.Builder()// TODO reuse!!!
        .baseUrl(BASE_URL)
        .client(
            Builder()
                .addInterceptor(BasicAuthInterceptor())
                .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RedditAccessTokenApi::class.java)

    override suspend fun getAccessToken(deviceId: String): AccessToken {
        val s = "${BuildConfig.CLIENT_ID}:"
        return mRedditAccessTokenApi.getAccessToken(
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