package com.pietrantuono.data.api

import com.pietrantuono.data.model.AccessToken
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RedditAccessTokenApi {

    @FormUrlEncoded
    @POST("api/v1/access_token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("device_id") deviceId: String
    ): AccessToken

}


