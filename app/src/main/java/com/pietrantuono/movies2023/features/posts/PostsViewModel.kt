package com.pietrantuono.movies2023.features.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pietrantuono.domain.GetPostsUseCase
import com.pietrantuono.domain.GetPostsUseCase.Params.Initial
import com.pietrantuono.movies2023.features.posts.Action.GetInitialPosts
import com.pietrantuono.movies2023.features.posts.Action.GetNextPosts
import com.pietrantuono.movies2023.features.posts.UiState.Content
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.function.Consumer
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val useCase: GetPostsUseCase,
    private val coroutineContext: CoroutineContext,
) : ViewModel(), Consumer<Action> {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(Content())
    val uiState: Flow<UiState> = _uiState

    override fun accept(action: Action) {
        when (action) {
            is GetInitialPosts -> getInitialPosts()
            is GetNextPosts -> TODO()
        }
    }

    private fun getInitialPosts() {
        launch {
            try {
                _uiState.value = Content(useCase.execute(Initial))
            } catch (e: Exception) {
                // TODO
            }
        }
    }

    private fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(coroutineContext, block = block)
    }
}