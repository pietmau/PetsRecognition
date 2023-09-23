package com.pietrantuono.data.model

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("access_token") var accessToken: String? = null,
    @SerializedName("token_type") var tokenType: String? = null,
    @SerializedName("device_id") var deviceId: String? = null,
    @SerializedName("expires_in") var expiresIn: Int? = null,
    @SerializedName("scope") var scope: String? = null
)