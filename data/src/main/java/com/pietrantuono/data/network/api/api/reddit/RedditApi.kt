package com.pietrantuono.data.network.api.api.reddit

import com.pietrantuono.data.network.model.reddit.NetowrkRedditResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RedditApi {

    @GET("/r/{subReddit}/top/")
    suspend fun getSubRedditTop(
        @Path("subReddit") subReddit: String,
        @QueryMap queryMap: Map<String, String>
    ): NetowrkRedditResponse

    @GET("/r/{subReddit}/top/")
    fun getSubRedditTopCall(
        @Path("subReddit") subReddit: String,
        @QueryMap queryMap: Map<String, String>
    ): Call<NetowrkRedditResponse>
}
