package com.pietrantuono.data.api.api.accesstoken

import com.pietrantuono.data.model.accesstoken.AccessToken
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AccessTokenApi {

    @FormUrlEncoded
    @POST("api/v1/access_token")
    fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("device_id") deviceId: String
    ): Call<AccessToken>

}


