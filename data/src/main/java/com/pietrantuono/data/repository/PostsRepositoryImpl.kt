package com.pietrantuono.data.repository

import android.util.Log
import com.pietrantuono.data.database.DatabaseClient
import com.pietrantuono.data.network.api.client.RedditApiClient
import com.pietrantuono.data.networkchecker.NetworkChecker
import com.pietrantuono.domain.PostsRepository
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val apiClient: RedditApiClient,
    private val databaseClient: DatabaseClient,
    private val networkChecker: NetworkChecker,
) : PostsRepository {

    override suspend fun getLatestPosts(): List<Post> {
        return if (!networkChecker.isNetworkAvailable()) {
            databaseClient.getLatestPosts(defaultPageSize)
        } else {
            getAndSavePostFromApi(limit = defaultPageSize)
            databaseClient.getLatestPosts(defaultPageSize)
        }
    }

    override suspend fun getNextPosts(date: Long, page: String, limit: Int): List<Post> {
        if (!networkChecker.isNetworkAvailable()) {
            return databaseClient.getPostsAfter(date, limit)
        }
        getAndSavePostFromApi(page, limit)
        return databaseClient.getPostsAfter(date, limit)
    }

    private suspend fun getAndSavePostFromApi(page: String? = null, limit: Int? = null) {
        try {
            val posts = apiClient.getSubReddit(
                subReddit = SUBREDDIT,
                after = page,
                limit = limit
            )
            databaseClient.insertPosts(posts)
        } catch (e: Exception) {
            e.printStackTrace()// TODO
        }
    }

    private companion object {
        private const val SUBREDDIT = "funny" // Inject
    }
}