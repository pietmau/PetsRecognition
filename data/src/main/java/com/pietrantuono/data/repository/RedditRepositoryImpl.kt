package com.pietrantuono.data.repository

import com.pietrantuono.data.database.DatabaseClient
import com.pietrantuono.data.network.api.client.RedditApiClient
import com.pietrantuono.data.networkchecker.NetworkChecker
import com.pietrantuono.domain.RedditRepository
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class RedditRepositoryImpl @Inject constructor(
    private val apiClient: RedditApiClient,
    private val databaseClient: DatabaseClient,
    private val networkChecker: NetworkChecker,
) : RedditRepository {

    override suspend fun getLatestPosts() = if (!networkChecker.isNetworkAvailable()) {
        databaseClient.getLatestPosts()
    } else {
        getPostFromApi()
    }

    override suspend fun getNextPosts(index: Long, page: String, limit: Int): List<Post> {
        val posts = databaseClient.getPostsAfter(index, limit)
        return posts.takeIf { it.isNotEmpty() } ?: getPostFromApi(page, limit).also {
            databaseClient.insertPosts(it, page)
        }
    }

    private suspend fun getPostFromApi(page: String? = null, limit: Int? = null) = try {
        apiClient.getSubReddit(
            subReddit = SUBREDDIT,
            after = page,
            limit = limit
        ).also { databaseClient.insertPosts(it, page) }
    } catch (e: Exception) {
        emptyList()// TODO
    }

    private companion object {
        private const val SUBREDDIT = "funny" // Inject
    }
}