package com.pietrantuono.movies2023.features.posts.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.APPEND
import androidx.paging.LoadType.PREPEND
import androidx.paging.LoadType.REFRESH
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.MediatorResult.Error
import androidx.paging.RemoteMediator.MediatorResult.Success
import com.pietrantuono.data.database.RedditDao
import com.pietrantuono.domain.RedditRepository
import com.pietrantuono.domain.model.reddit.Post

@OptIn(ExperimentalPagingApi::class)
class RedditRemoteMediator(
    private val repository: RedditRepository,
    private val redditDao: RedditDao,

) : RemoteMediator<String, Post>() {

    override suspend fun initialize() = InitializeAction.LAUNCH_INITIAL_REFRESH

    override suspend fun load(loadType: LoadType, state: PagingState<String, Post>): MediatorResult {
        val key = when (loadType) {
            REFRESH -> state.getNextItem()
            PREPEND -> state.getNextItem() ?: return Success(endOfPaginationReached = true)// TODO endOfPaginationReached???
            APPEND -> state.getPreviousItem() ?: return Success(endOfPaginationReached = true)
        }

        return try {
            val posts = when (loadType) {
                PREPEND -> repository.getPosts(before = key, limit = state.config.pageSize)
                else -> repository.getPosts(after = key, limit = state.config.pageSize)
            }

            Error(TODO())
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }

    private fun PagingState<String, Post>.getNextItem() = lastItemOrNull()?.name

    private fun PagingState<String, Post>.getPreviousItem() = firstItemOrNull()?.name

}