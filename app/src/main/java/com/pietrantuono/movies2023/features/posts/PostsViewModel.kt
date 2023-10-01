package com.pietrantuono.movies2023.features.posts

import android.util.Log
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
        Log.e("PostsViewModel", "accept: $action")
        when (action) {
            is GetInitialPosts -> getInitialPosts()
            is GetNextPosts -> getNextPosts(action.indexOfLastItem)
        }
    }

    private fun getInitialPosts() {
        Log.e("PostsViewModel", "getInitialPosts")
        launch {
            val posts = useCase.execute(Initial)
            Log.e("PostsViewModel", "getInitialPosts " + posts.size + " " + posts.map { it.title }.joinToString(separator = " -- ") { it.toString() })
            updateState { copy(posts = this.posts + posts) }
        }
    }

    private fun getNextPosts(indexOfLastItem: Int) {
        Log.e("PostsViewModel", "getNextPosts")
        val data = (_uiState.value as? Content)?.posts
        val nextPage = data?.getOrNull(indexOfLastItem) ?: return
        launch {
            val posts = useCase.execute(GetPostsUseCase.Params.Next(nextPage.createdUtc ?: 0, nextPage.name))
            Log.e("PostsViewModel", "getNextPosts " + posts.size + " " + posts.map { it.title }.joinToString(separator = " -- ") { it.toString() })
            updateState { copy(posts = data + posts) }
        }
    }

    private fun updateState(reducer: Content.() -> UiState) {
        val currentState = _uiState.value as? Content ?: return
        val newState = currentState.reducer()
        if (newState != currentState) {
            _uiState.value = newState
        }
    }

    private fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(coroutineContext) {
            try {
                block()
            } catch (e: Exception) {
                // TODO
            }
        }
    }
}