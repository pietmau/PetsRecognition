package com.pietrantuono.data.repository

import android.util.Log
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

    override suspend fun getLatestPosts(): List<Post> {
        Log.e("RedditRepositoryImpl", "getLatestPosts")
        return if (!networkChecker.isNetworkAvailable()) {
            databaseClient.getLatestPosts(defaultPageSize)
        } else {
            getAndSavePostFromApi(limit = defaultPageSize)
            databaseClient.getLatestPosts(defaultPageSize)
        }
    }

    override suspend fun getNextPosts(index: Long, page: String, limit: Int): List<Post> {
        Log.e("RedditRepositoryImpl", "getNextPosts: $index, $page, $limit")
        val posts = databaseClient.getPostsAfter(index, limit)
        if (posts.isNotEmpty()) return posts
        getAndSavePostFromApi(page, limit)
        return databaseClient.getPostsAfter(index, limit)
    }

    private suspend fun getAndSavePostFromApi(page: String? = null, limit: Int? = null) {
        Log.e("RedditRepositoryImpl", "getAndSavePostFromApi: $page, $limit")
        try {
            val posts = apiClient.getSubReddit(
                subReddit = SUBREDDIT,
                after = page,
                limit = limit
            )
            databaseClient.insertPosts(posts)
        } catch (e: Exception) {
            e.printStackTrace()
            TODO()
        }
    }

    private companion object {
        private const val SUBREDDIT = "funny" // Inject
    }
}