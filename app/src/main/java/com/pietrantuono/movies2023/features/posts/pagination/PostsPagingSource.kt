package com.pietrantuono.movies2023.features.posts.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pietrantuono.domain.GetPostsUseCase
import com.pietrantuono.domain.model.reddit.Posts
import javax.inject.Inject

/**
 * A [PagingSource] that loads articles for paging. The [Int] is the paging key or query that is used to fetch more
 * data, and the [Article] specifies that the [PagingSource] fetches an [Article] [List].
 */

private val STARTING_KEY: String? = "t3_d9gvok"

class PostsPagingSource @Inject constructor(
    private val useCase: GetPostsUseCase
) : PagingSource<String, Pair<String, String>>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Pair<String, String>> {
        // If params.key is null, it is the first load, so we start loading with STARTING_KEY
        val startKey = params.key ?: STARTING_KEY

        // We fetch as many articles as hinted to by params.loadSize
        //val range = startKey.until(startKey + params.loadSize)
        Log.e("foo", params.toString())
        Log.e("foo", "startKey " + startKey + " params.loadSize " + params.loadSize)
        var result: Posts? = null
        if (params is LoadParams.Prepend) {
            result = useCase.execute(GetPostsUseCase.Params(before = startKey, limit = params.loadSize))
        } else {
            result = useCase.execute(GetPostsUseCase.Params(after = startKey, limit = params.loadSize))
        }
        // Simulate a delay for loads adter the initial load
        //if (startKey != STARTING_KEY) delay(LOAD_DELAY_MILLIS)
        val data: List<Pair<String, String>> = result.posts.map { it.data }.map {
            if (it?.name == null) Log.e("foo", "it.name is null for itme ${it?.title} ${it?.name}")
            (it?.title ?: "") to (it?.name ?: "")

        }.filterNotNull()

        return LoadResult.Page(
            data = data,
            prevKey = result.before,
            nextKey = result.after
        )
    }

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<String, Pair<String, String>>): String {
        Log.e("foo", "getRefreshKey")
        return state.anchorPosition?.let { state.closestItemToPosition(it)?.second }!!
    }

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    //private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}