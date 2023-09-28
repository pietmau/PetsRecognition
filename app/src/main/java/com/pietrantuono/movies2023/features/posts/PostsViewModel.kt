package com.pietrantuono.movies2023.features.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pietrantuono.domain.GetPostsUseCase
import com.pietrantuono.domain.model.reddit.Posts
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.function.Consumer
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

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
        viewModelScope.launch(Dispatchers.IO) {// Inject
            val posts: Posts = useCase.execute(GetPostsUseCase.Params())
            val data = posts.posts.map { it.data }.map { it?.title }.filterNotNull()
            _uiState.value = UiState.Content(data)
        }
    }

    val items: Flow<PagingData<String>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { repository.articlePagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)
}