package com.pietrantuono.movies2023.features.posts.ui

import androidx.compose.runtime.Composable
import com.pietrantuono.movies2023.features.posts.PostsUiEvent
import com.pietrantuono.movies2023.features.posts.PostsUiState
import com.pietrantuono.movies2023.features.posts.PostsUiState.Content

@Composable
fun PostsScreen(viewState: PostsUiState, uiEvents: (PostsUiEvent) -> Unit) {
    when (val state = viewState) {
        is Content -> Content(state) { event ->
            uiEvents(event)
        }
    }
}
