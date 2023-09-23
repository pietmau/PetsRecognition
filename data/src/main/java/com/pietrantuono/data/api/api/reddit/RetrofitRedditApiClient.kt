package com.pietrantuono.data.api.api.reddit

import com.pietrantuono.data.api.authenticator.RedditAuthenticator
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRedditApiClient @Inject constructor(
    authenticator: RedditAuthenticator,
    bearerTokenAuthInterceptor: Interceptor
) : RedditApiClient {

    private var redditApi = Retrofit.Builder()// TODO reuse!!!
        .baseUrl(BASE_URL)
        .client(
            Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
                .authenticator(authenticator)
                .addInterceptor(bearerTokenAuthInterceptor)
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RedditApi::class.java)


    override suspend fun getSubReddit(
        subReddit: String,
        limit: Int?,
        before: String?,
        after: String?,
        query: String?
    ) = redditApi.getSubReddit()

    private companion object {
        private const val BASE_URL = "https://oauth.reddit.com" // TODO move to gradle
    }
}
