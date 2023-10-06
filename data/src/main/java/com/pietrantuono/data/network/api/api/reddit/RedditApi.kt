package com.pietrantuono.data.network.api.api.reddit

import com.pietrantuono.data.network.entity.reddit.NetowrkRedditResponseEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RedditApi {

    @GET("/r/{subReddit}/new/")
    suspend fun getSubReddit(
        @Path(SUBREDDIT) subReddit: String,
        @QueryMap queryMap: Map<String, String>,
    ): NetowrkRedditResponseEntity

    @GET("/r/{subReddit}/top/")
    fun getSubRedditTopCall(
        @Path(SUBREDDIT) subReddit: String,
        @QueryMap queryMap: Map<String, String>,
    ): Call<NetowrkRedditResponseEntity>


    private companion object {
        private const val SUBREDDIT = "subReddit"
    }
}
