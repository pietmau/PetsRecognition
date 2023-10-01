package com.pietrantuono.data.networkchecker

interface NetworkChecker {

    suspend fun isNetworkAvailable(): Boolean
}