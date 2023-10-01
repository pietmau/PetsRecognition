package com.pietrantuono.movies2023.features.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pietrantuono.domain.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.function.Consumer
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val useCase: GetPostsUseCase,
    private val coroutineContext: CoroutineContext,
) : ViewModel(), Consumer<Action> {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Content())
    val uiState: Flow<UiState> = _uiState

    override fun accept(action: Action) {
        when (action) {
            is Action.GetInitialPosts -> getInitialPosts()
        }
    }

    private fun getInitialPosts() {
        viewModelScope.launch(coroutineContext) {
            try {
                val posts = useCase.execute(GetPostsUseCase.Params())
                _uiState.value = UiState.Content(posts) // TODO update the state
            } catch (e: Exception) {
                // TODO
                foo()
            }
        }
    }

    private fun foo() {

    }

}