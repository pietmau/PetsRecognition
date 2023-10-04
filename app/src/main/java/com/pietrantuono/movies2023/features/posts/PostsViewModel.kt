package com.pietrantuono.movies2023.features.posts

import com.pietrantuono.domain.GetPostsUseCase
import com.pietrantuono.domain.GetPostsUseCase.Params.Initial
import com.pietrantuono.domain.GetPostsUseCase.Params.Next
import com.pietrantuono.movies2023.common.RedditViewModel
import com.pietrantuono.movies2023.features.Destination.Detail
import com.pietrantuono.movies2023.features.Destination.None
import com.pietrantuono.movies2023.features.posts.PostsUiEvent.GetInitialPosts
import com.pietrantuono.movies2023.features.posts.PostsUiEvent.GetNextPosts
import com.pietrantuono.movies2023.features.posts.PostsUiEvent.NavigationPerformed
import com.pietrantuono.movies2023.features.posts.PostsUiEvent.PostClicked
import com.pietrantuono.movies2023.features.posts.PostsUiState.Content
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val useCase: GetPostsUseCase,
    coroutineContext: CoroutineContext,
) : RedditViewModel<PostsUiState, PostsUiEvent>(coroutineContext) {

    override val _uiState: MutableStateFlow<PostsUiState> = MutableStateFlow(Content())

    override fun accept(action: PostsUiEvent) {
        when (action) {
            is GetInitialPosts -> getInitialPosts()
            is GetNextPosts -> getNextPosts(action.indexOfLastItem)
            is NavigationPerformed -> updateState { copy(navDestination = None) }
            is PostClicked -> updateState { copy(navDestination = Detail(action.post.id)) }
        }
    }

    private fun getInitialPosts() {
        launch {
            val posts = useCase.execute(Initial)
            updateState { copy(posts = posts) }
        }
    }

    private fun getNextPosts(indexOfLastItem: Int) {
        val data = (_uiState.value as? Content)?.posts
        val nextPage = data?.getOrNull(indexOfLastItem) ?: return
        launch {
            val newPosts = useCase.execute(Next(nextPage.createdUtc, nextPage.name))
            updateState { copy(posts = posts + newPosts) }
        }
    }

    private fun updateState(reducer: Content.() -> Content) {
        val currentState = _uiState.value as? Content ?: return
        val newState = currentState.reducer()
        if (newState != currentState) {
            _uiState.value = newState
        }
    }
}
