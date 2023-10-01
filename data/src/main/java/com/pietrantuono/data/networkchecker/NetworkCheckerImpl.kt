package com.pietrantuono.data.networkchecker

import android.net.ConnectivityManager
import android.os.Build
import javax.inject.Inject

class NetworkCheckerImpl @Inject constructor(private val connectivityManager: ConnectivityManager) : NetworkChecker {
    override suspend fun isNetworkAvailable() = isInternetConnected() && canPingGoogle()

    private fun isInternetConnected() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork != null && connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) != null
        } else {
            connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnectedOrConnecting
        }

    private fun canPingGoogle() =
        try {
            val a: Int = Runtime.getRuntime().exec(CMD_PING_GOOGLE).waitFor()
            a == 0x0
        } catch (ioE: Exception) {
            false
        }

    private companion object {
        private const val CMD_PING_GOOGLE = "ping -w -c 1 google.com"
    }
}