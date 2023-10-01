package com.pietrantuono.data.networkchecker

import android.net.ConnectivityManager
import javax.inject.Inject


class NetworkCheckerImpl @Inject constructor(private val connectivityManager: ConnectivityManager) : NetworkChecker {
    override suspend fun isNetworkAvailable() =
        connectivityManager.activeNetwork != null && connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) != null

    private companion object {
        private const val GOOGLE = "google.com"
    }
}