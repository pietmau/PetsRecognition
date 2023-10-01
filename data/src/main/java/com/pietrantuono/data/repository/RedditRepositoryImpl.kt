package com.pietrantuono.data.repository

import com.pietrantuono.data.database.DatabaseClient
import com.pietrantuono.data.network.api.client.RedditApiClient
import com.pietrantuono.data.networkchecker.NetworkChecker
import com.pietrantuono.domain.RedditRepository
import javax.inject.Inject

class RedditRepositoryImpl @Inject constructor(
    private val apiClient: RedditApiClient,
    private val databaseClient: DatabaseClient,
    private val networkChecker: NetworkChecker,
) : RedditRepository {

    override suspend fun getLatestPosts() =
        if (!networkChecker.isNetworkAvailable()) {
            databaseClient.getLatestPosts()
        } else {
            getPostFromApi()
        }

    private suspend fun getPostFromApi(page: String? = null, limit: Int? = null) =
        try {
            apiClient.getSubReddit(
                subReddit = SUBREDDIT,
                after = page,
                limit = limit
            ).also { databaseClient.insertPosts(it, page) }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()// TODO
        }

    private companion object {
        private const val SUBREDDIT = "funny" // Inject
    }
}