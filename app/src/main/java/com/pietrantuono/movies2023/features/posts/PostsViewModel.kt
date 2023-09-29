package com.pietrantuono.movies2023.features.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pietrantuono.domain.GetPostsUseCase
import com.pietrantuono.domain.model.reddit.Post
import com.pietrantuono.movies2023.features.posts.pagination.PostsPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.function.Consumer
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val useCase: GetPostsUseCase
) : ViewModel(), Consumer<Action> { // TODO use generics

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Content())

    val uiState: Flow<UiState> = _uiState
    override fun accept(action: Action) {
        when (action) {
            is Action.GetPosts -> getPosts()
        }
    }

    private fun getPosts() {
    }

    val items: Flow<PagingData<Post>> = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { PostsPagingSource(useCase) }
    )
        .flow
        .cachedIn(viewModelScope)

}