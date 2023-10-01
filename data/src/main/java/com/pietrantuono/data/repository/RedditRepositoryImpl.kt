package com.pietrantuono.data.repository

import com.pietrantuono.data.database.DatabaseClient
import com.pietrantuono.data.network.api.client.RedditApiClient
import com.pietrantuono.data.networkchecker.NetworkChecker
import com.pietrantuono.domain.RedditRepository

class RedditRepositoryImpl(
    private val apiClient: RedditApiClient,
    private val databaseClient: DatabaseClient,
    private val networkChecker: NetworkChecker,
) : RedditRepository {

    override suspend fun getPosts(before: String?, after: String?, limit: Int?) =
        if (!networkChecker.isNetworkAvailable()) {
            databaseClient.getPosts(after, limit)
        } else {
            databaseClient.getPosts(after, limit).ifEmpty {
                getPostFromApi(after, limit)
            }
        }

    private suspend fun getPostFromApi(page: String?, limit: Int?) = try {
        apiClient.getSubReddit(
            subReddit = SUBREDDIT,
            after = page,
            limit = limit
        ).also { databaseClient.insertPosts(it, page) }
    } catch (e: Exception) { // TODO
        emptyList()
    }

    private companion object {
        private const val SUBREDDIT = "funny" // Inject
    }
}