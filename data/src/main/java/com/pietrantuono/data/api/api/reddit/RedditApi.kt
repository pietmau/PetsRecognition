package com.pietrantuono.data.api.api.reddit

import com.pietrantuono.data.model.reddit.RedditResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RedditApi {

    @GET("/r/memes/top/?t=all") // TODO add page and limit
    //@GET("/{subReddit}/?t=all") // TODO add page and limit
    suspend fun getsubReddit(
        @Path("subReddit") subReddit: String? = null,
        @QueryMap queryMap: Map<String, String>? = null
    ): RedditResponse

}


