package com.pietrantuono.movies2023.features.posts.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams.Prepend
import androidx.paging.PagingState
import com.pietrantuono.domain.GetPostsUseCase
import com.pietrantuono.domain.GetPostsUseCase.Params
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class RedditPagingSource @Inject constructor(
    private val useCase: GetPostsUseCase
) : PagingSource<String, Post>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Post> {
        val posts = posts(params)
        return LoadResult.Page(
            data = posts,
            prevKey = posts.firstOrNull()?.name,
            nextKey = posts.lastOrNull()?.name
        )
    }

    private suspend fun posts(params: LoadParams<String>) =
        if (params is Prepend) {
            useCase.execute(Params(before = params.key, limit = params.loadSize))
        } else {
            useCase.execute(Params(after = params.key, limit = params.loadSize))
        }

    override fun getRefreshKey(state: PagingState<String, Post>) = state.anchorPosition?.let { state.closestItemToPosition(it)?.name }
}