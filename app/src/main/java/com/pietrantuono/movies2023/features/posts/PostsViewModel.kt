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
        when (action) {
            is GetInitialPosts -> getInitialPosts()
            is GetNextPosts -> getNextPosts(action.indexOfLastItem)
        }
    }

    private fun getInitialPosts() {
        launch {
            val posts = useCase.execute(Initial)
            updateState { copy(data = data + posts) }
        }
    }

    private fun getNextPosts(indexOfLastItem: Int) {
        val data = (_uiState.value as? Content)?.data
        val nextPage = data?.getOrNull(indexOfLastItem) ?: return
        launch {
            val posts = useCase.execute(GetPostsUseCase.Params.Next(indexOfLastItem, nextPage.page, nextPage.limit))
            updateState { copy(data = data + posts) }
        }
        foo()
    }

    private fun foo() {

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