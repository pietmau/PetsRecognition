package com.pietrantuono.data.networkchecker

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.os.Build
import javax.inject.Inject

class NetworkCheckerImpl @Inject constructor(private val connectivityManager: ConnectivityManager) : NetworkChecker {
    override suspend fun isNetworkAvailable() = isInternetConnected()

    @SuppressLint("MissingPermission")
    private fun isInternetConnected() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager.activeNetwork?.let { connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) != null } == true
    } else {
        connectivityManager.activeNetworkInfo?.let { connectivityManager.activeNetworkInfo?.isConnectedOrConnecting } == true
    }

    private fun canPingGoogle() = try {
        Runtime.getRuntime().exec(CMD_PING_GOOGLE).waitFor() == 0x0
    } catch (ioE: Exception) {
        false
    }

    private companion object {
        private const val CMD_PING_GOOGLE = "ping -w -c 1 google.com"
    }
}