package com.pietrantuono.data.networkchecker

import android.net.ConnectivityManager
import javax.inject.Inject


// We need someting that returns quickly.
class NetworkCheckerImpl @Inject constructor(private val connectivityManager: ConnectivityManager) : NetworkChecker {
    override suspend fun isNetworkAvailable() =
        connectivityManager.activeNetwork != null && connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) != null
}