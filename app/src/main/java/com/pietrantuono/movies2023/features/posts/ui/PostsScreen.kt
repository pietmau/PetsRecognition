package com.pietrantuono.movies2023.features.posts.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.pietrantuono.movies2023.features.posts.PostsUiEvent
import com.pietrantuono.movies2023.features.posts.PostsUiState
import com.pietrantuono.movies2023.features.posts.PostsUiState.Content

@Composable
fun PostsScreen(navigateTo: PostsUiState, function: (PostsUiEvent) -> Unit) {
    when (val state = viewState) {
        is Content -> Content(state) {
            viewModel.accept(it)
        }
    }
    LaunchedEffect(viewState.navDestination) {
        navigateTo(viewState.navDestination)
        viewModel.accept(PostsUiEvent.NavigationPerformed)
    }
}
