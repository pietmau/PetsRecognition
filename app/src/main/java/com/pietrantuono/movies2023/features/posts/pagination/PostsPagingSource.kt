package com.pietrantuono.movies2023.features.posts.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pietrantuono.domain.GetPostsUseCase
import javax.inject.Inject

/**
 * A [PagingSource] that loads articles for paging. The [Int] is the paging key or query that is used to fetch more
 * data, and the [Article] specifies that the [PagingSource] fetches an [Article] [List].
 */

private val STARTING_KEY: String? = null
private const val LOAD_DELAY_MILLIS = 3_000L

class PostsPagingSource @Inject constructor(
    private val useCase: GetPostsUseCase
) : PagingSource<String, String>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, String> {
        // If params.key is null, it is the first load, so we start loading with STARTING_KEY
        val startKey = params.key ?: STARTING_KEY

        // We fetch as many articles as hinted to by params.loadSize
        //val range = startKey.until(startKey + params.loadSize)

        val result = useCase.execute(GetPostsUseCase.Params(after = startKey, limit = params.loadSize))
        // Simulate a delay for loads adter the initial load
        //if (startKey != STARTING_KEY) delay(LOAD_DELAY_MILLIS)
        return LoadResult.Page(
            data = result.posts.map { it.data }.map { it?.title }.filterNotNull(),
            prevKey = result.before,
            nextKey = result.after
        )
    }

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<String, String>): String? {
        // In our case we grab the item closest to the anchor position
        // then return its id - (state.config.pageSize / 2) as a buffer
//        val anchorPosition = state.anchorPosition ?: return null
//        val article = state.closestItemToPosition(anchorPosition) ?: return null
//        return ensureValidKey(key = article.id - (state.config.pageSize / 2))
        return null
    }

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    //private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}