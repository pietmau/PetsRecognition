package com.pietrantuono.data.network.api.tokenmanager

import android.content.SharedPreferences
import java.util.UUID
import javax.inject.Inject

class SharedPreferencesTokenManager @Inject constructor(private val sharedPreferences: SharedPreferences) : TokenManager {
    override fun getToken(): String? = sharedPreferences.getString(TOKEN, null)

    override fun setToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }

    override fun getDeviceId() =
        sharedPreferences.getString(DEVICE_ID, null) ?: run {
            val deviceId = UUID.randomUUID().toString()
            sharedPreferences.edit().putString(DEVICE_ID, deviceId).apply()
            deviceId
        }

    private companion object {
        private const val TOKEN = "token"
        private const val DEVICE_ID = "device_id"
    }
}