package com.pietrantuono.data.network.api.api.reddit

import com.pietrantuono.data.network.api.interceptor.BearerTokenAuthInterceptor
import com.pietrantuono.data.network.model.reddit.NetowrkRedditResponse
import javax.inject.Inject
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRedditApiClient @Inject constructor(
    bearerTokenAuthInterceptor: BearerTokenAuthInterceptor
) : RedditApiClient {

    private var redditApi = Retrofit.Builder()// TODO reuse!!!
        .baseUrl(BASE_URL)
        .client(
            Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
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
    ): NetowrkRedditResponse {
        val queryMap = mutableMapOf<String, String>().apply {
            limit?.let { this["limit"] = it.toString() }
            before?.let { this["before"] = it }
            after?.let { this["after"] = it }
            query?.let { this["?"] = it }
        }
        return redditApi.getSubReddit(subReddit, queryMap)
    }

    private companion object {
        private const val BASE_URL = "https://oauth.reddit.com" // TODO move to gradle
    }
}
