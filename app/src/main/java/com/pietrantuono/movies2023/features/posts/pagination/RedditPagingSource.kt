package com.pietrantuono.movies2023.features.posts.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pietrantuono.data.database.DatabaseClient
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class RedditPagingSource @Inject constructor(
    private val redditDatabaseClient: DatabaseClient,
) : PagingSource<String, Post>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Post> {
        Log.e("RedditPagingSource", "LOAD: $params key=${params.key}")
        if(params is LoadParams.Prepend) {
            return LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null,
            )
        }
        val posts = posts(params.key, params.loadSize)
        Log.e("RedditPagingSource", "posts (${posts.size} ): $posts")
        val prevKey = posts.firstOrNull()?.before
        Log.e("RedditPagingSource", "prevKey: $prevKey")
        val nextKey = posts.lastOrNull()?.after
        Log.e("RedditPagingSource", "nextKey: $nextKey")
        return LoadResult.Page(
            data = posts,
            prevKey = prevKey,
            nextKey = nextKey,
        )
    }

    private suspend fun posts(params: String?, loadSize: Int) = redditDatabaseClient.getPosts(params, loadSize)

    override fun getRefreshKey(state: PagingState<String, Post>) = state.anchorPosition?.let { state.closestItemToPosition(it)?.name }
}