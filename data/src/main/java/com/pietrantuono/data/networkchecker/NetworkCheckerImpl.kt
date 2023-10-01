package com.pietrantuono.data.networkchecker

import java.net.InetAddress
import javax.inject.Inject


class NetworkCheckerImpl @Inject constructor() : NetworkChecker {
    override suspend fun isNetworkAvailable() = try {
        val inetAddress = InetAddress.getByName(GOOGLE)
        !inetAddress.equals("")
    } catch (e: Exception) {
        false
    }

    private companion object {
        private const val GOOGLE = "google.com"
    }
}