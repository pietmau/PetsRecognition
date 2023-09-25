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
                .addInterceptor(bearerTokenAuthInterceptor)
                .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
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
            this["t"] = "all"
            // Explanation https://www.reddit.com/r/redditdev/comments/d8zl00/comment/f1g505p/?utm_source=share&utm_medium=web2x&context=3
            this["count"]= "555"
            limit?.let { this["limit"] = it.toString() }
            before?.let { this["before"] = it }
            after?.let { this["after"] = it }
        }
        return redditApi.getSubRedditTop(subReddit, queryMap)
    }

    private companion object {
        private const val BASE_URL = "https://oauth.reddit.com" // TODO move to gradle
    }
}
