package com.pietrantuono.data.network.api.api.reddit

import com.pietrantuono.data.network.model.reddit.NetowrkRedditResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RedditApi {

    //@GET("/r/memes/top/?t=all") // TODO add page and limit
    @GET("/r/memes/top/?t=all&count=555") // TODO add page and limit
    suspend fun getSubReddit(
        //@Path("subReddit") subReddit: String,
        @QueryMap queryMap: Map<String, String>
    ): NetowrkRedditResponse

}


