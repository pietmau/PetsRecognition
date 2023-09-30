package com.pietrantuono.movies2023.features.posts.pagination

import androidx.paging.PagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SuspendingPagingSourceFactory<Key : Any, Value : Any>( // TODO remove
    private val dispatcher: CoroutineDispatcher,
    private val delegate: () -> PagingSource<Key, Value>,
) : () -> PagingSource<Key, Value> {
    suspend fun create(): PagingSource<Key, Value> {
        return withContext(dispatcher) {
            delegate()
        }
    }

    override fun invoke(): PagingSource<Key, Value> {
        return delegate()
    }
}
