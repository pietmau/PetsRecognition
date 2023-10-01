package com.pietrantuono.movies2023.features.posts

import com.pietrantuono.domain.GetPostsUseCase
import com.pietrantuono.domain.GetPostsUseCase.Params.Initial
import com.pietrantuono.domain.GetPostsUseCase.Params.Next
import com.pietrantuono.movies2023.common.RedditViewModel
import com.pietrantuono.movies2023.features.posts.Destination.Detail
import com.pietrantuono.movies2023.features.posts.Destination.None
import com.pietrantuono.movies2023.features.posts.PostsAction.GetInitialPosts
import com.pietrantuono.movies2023.features.posts.PostsAction.GetNextPosts
import com.pietrantuono.movies2023.features.posts.PostsAction.NavigationPerformed
import com.pietrantuono.movies2023.features.posts.PostsAction.PostClicked
import com.pietrantuono.movies2023.features.posts.PostsViewState.Content
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetPostsUseCase,
    coroutineContext: CoroutineContext,
) : RedditViewModel<PostsViewState, PostsAction>(coroutineContext) {

    override val _viewState: MutableStateFlow<PostsViewState> = MutableStateFlow(Content())

    override fun accept(postsAction: PostsAction) {
        when (postsAction) {
            is GetInitialPosts -> getInitialPosts()
            is GetNextPosts -> getNextPosts(postsAction.indexOfLastItem)
            is NavigationPerformed -> updateState { copy(navDestination = None) }
            is PostClicked -> updateState { copy(navDestination = Detail(postsAction.post.name)) }
        }
    }

    private fun getInitialPosts() {
        launch {
            val posts = useCase.execute(Initial)
            updateState { copy(posts = posts) }
        }
    }

    private fun getNextPosts(indexOfLastItem: Int) {
        val data = (_viewState.value as? Content)?.posts
        val nextPage = data?.getOrNull(indexOfLastItem) ?: return
        launch {
            val newPosts = useCase.execute(Next(nextPage.createdUtc, nextPage.name))
            updateState { copy(posts = posts + newPosts) }
        }
    }

    private fun updateState(reducer: Content.() -> Content) {
        val currentState = _viewState.value as? Content ?: return
        val newState = currentState.reducer()
        if (newState != currentState) {
            _viewState.value = newState
        }
    }
}
