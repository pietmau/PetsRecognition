package com.pietrantuono.movies2023.features.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pietrantuono.domain.GetPostsUseCase
import com.pietrantuono.domain.GetPostsUseCase.Params.Initial
import com.pietrantuono.movies2023.features.posts.Action.GetInitialPosts
import com.pietrantuono.movies2023.features.posts.Action.GetNextPosts
import com.pietrantuono.movies2023.features.posts.Action.NavigationPerformed
import com.pietrantuono.movies2023.features.posts.Action.PostClicked
import com.pietrantuono.movies2023.features.posts.Destination.Detail
import com.pietrantuono.movies2023.features.posts.Destination.None
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
class MainViewModel @Inject constructor(
    private val useCase: GetPostsUseCase,
    private val coroutineContext: CoroutineContext,
) : ViewModel(), Consumer<Action> {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(Content())
    val uiState: Flow<UiState> = _uiState

    override fun accept(action: Action) {
        when (action) {
            is GetInitialPosts -> getInitialPosts()
            is GetNextPosts -> getNextPosts(action.indexOfLastItem)
            is NavigationPerformed -> updateState { copy(navDestination = None) }
            is PostClicked -> updateState { copy(navDestination = Detail(action.post.name)) }
        }
    }

    private fun getInitialPosts() {
        launch {
            val posts = useCase.execute(Initial)
            updateState { copy(posts = this.posts + posts) }
        }
    }

    private fun getNextPosts(indexOfLastItem: Int) {
        val data = (_uiState.value as? Content)?.posts
        val nextPage = data?.getOrNull(indexOfLastItem) ?: return
        launch {
            val posts = useCase.execute(GetPostsUseCase.Params.Next(nextPage.createdUtc ?: 0, nextPage.name))
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