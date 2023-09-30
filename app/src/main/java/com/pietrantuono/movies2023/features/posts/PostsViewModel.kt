package com.pietrantuono.movies2023.features.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.pietrantuono.domain.model.reddit.Post
import com.pietrantuono.movies2023.features.posts.pagination.RedditRemoteMediator
import com.pietrantuono.movies2023.features.posts.pagination.SuspendingPagingSourceFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.function.Consumer
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn

@HiltViewModel
class PostsViewModel @Inject constructor(
    factory: SuspendingPagingSourceFactory<String, Post>,
    mediator: RedditRemoteMediator,
) : ViewModel(), Consumer<Action> {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Content())

    val uiState: Flow<UiState> = _uiState
    override fun accept(action: Action) {
        when (action) {
            is Action.GetPosts -> getPosts()
        }
    }

    private fun getPosts() {
    }

    @OptIn(ExperimentalPagingApi::class)
    val items: Flow<PagingData<Post>> = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        remoteMediator = mediator,
        pagingSourceFactory = factory
    )
        .flow
        .flowOn(Dispatchers.IO)
        .cachedIn(viewModelScope)
}