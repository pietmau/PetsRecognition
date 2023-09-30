package com.pietrantuono.movies2023.features.posts.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams.Prepend
import androidx.paging.PagingState
import com.pietrantuono.data.database.RedditDao
import com.pietrantuono.domain.GetPostsUseCase
import com.pietrantuono.domain.GetPostsUseCase.Params
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class RedditPagingSource @Inject constructor(
    private val useCase: GetPostsUseCase,
    private val redditDao: RedditDao,
    private val mapper:FooMapper
) : PagingSource<String, Post>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Post> {
        Log.e("RedditPagingSource", "load: $params")
        val posts = posts(params)
        return LoadResult.Page(
            data = posts,
            prevKey = posts.firstOrNull()?.name,
            nextKey = posts.lastOrNull()?.name
        )
    }

    private suspend fun posts(params: LoadParams<String>) =
        if (params is Prepend) {
            redditDao.getBefore(params.key)
            useCase.execute(Params(before = params.key, limit = params.loadSize))
        } else {
            useCase.execute(Params(after = params.key, limit = params.loadSize))
        }

    override fun getRefreshKey(state: PagingState<String, Post>) = state.anchorPosition?.let { state.closestItemToPosition(it)?.name }
}