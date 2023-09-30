package com.pietrantuono.movies2023.features.posts.pagination

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pietrantuono.data.database.DatabaseClient
import com.pietrantuono.domain.GetPostsUseCase
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class RedditPagingSource @Inject constructor(
    private val redditDatabaseClient: DatabaseClient,
) : PagingSource<String, Post>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Post> {
        Log.e("RedditPagingSource", "load: $params")
        Log.e("RedditPagingSource", "thread: ${Thread.currentThread().name}")
        val posts = posts(params.key)
        Log.e("RedditPagingSource", "posts: $posts")
        return LoadResult.Page(
            data = posts,
            prevKey = posts.firstOrNull()?.before,
            nextKey = posts.lastOrNull()?.after,
        )
    }

    private suspend fun posts(params: String?) = redditDatabaseClient.getPosts(params)

    override fun getRefreshKey(state: PagingState<String, Post>) = state.anchorPosition?.let { state.closestItemToPosition(it)?.name }
}