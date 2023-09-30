package com.pietrantuono.movies2023.features.posts.pagination

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.APPEND
import androidx.paging.LoadType.PREPEND
import androidx.paging.LoadType.REFRESH
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.MediatorResult.Error
import androidx.paging.RemoteMediator.MediatorResult.Success
import com.pietrantuono.data.database.DatabaseClient
import com.pietrantuono.data.network.api.api.reddit.RedditApiClient
import com.pietrantuono.domain.model.reddit.Post

@OptIn(ExperimentalPagingApi::class)
class RedditRemoteMediator(
    private val apiClient: RedditApiClient,
    private val databaseClient: DatabaseClient,
) : RemoteMediator<String, Post>() {

    override suspend fun initialize() = InitializeAction.LAUNCH_INITIAL_REFRESH

    override suspend fun load(loadType: LoadType, state: PagingState<String, Post>): MediatorResult {
        val key = when (loadType) {
            REFRESH -> state.getNextItem()
            PREPEND -> state.getNextItem() ?: return Success(endOfPaginationReached = true)
            APPEND -> state.getPreviousItem() ?: return Success(endOfPaginationReached = true)
        }
        Log.e("RedditRemoteMediator", "loadType: $loadType, key: $key")
        return try {
            val posts = when (loadType) {
                PREPEND -> apiClient.getSubReddit(subReddit = FUNNY, before = key, limit = state.config.pageSize)
                else -> apiClient.getSubReddit(subReddit = FUNNY, after = key, limit = state.config.pageSize)
            }
            Log.e("RedditRemoteMediator", "posts: $posts")
            databaseClient.insertPosts(posts)
            Success(endOfPaginationReached = posts.isEmpty())
        } catch (exception: Exception) {
            Error(exception)
        }
    }

    private fun PagingState<String, Post>.getNextItem() = lastItemOrNull()?.name

    private fun PagingState<String, Post>.getPreviousItem() = firstItemOrNull()?.name

    private companion object {
        private const val FUNNY = "funny"
    }

}