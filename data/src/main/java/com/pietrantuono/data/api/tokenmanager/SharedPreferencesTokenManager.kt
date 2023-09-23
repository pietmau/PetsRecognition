package com.pietrantuono.data.api.tokenmanager

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesTokenManager @Inject constructor(private val sharedPreferences: SharedPreferences) : TokenManager {
    override fun getToken(): String? = sharedPreferences.getString(TOKEN, null)

    override fun setToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }

    private companion object {
        private const val TOKEN = "token"
    }
}