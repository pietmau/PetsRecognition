package com.pietrantuono.movies2023.features.posts.ui

import androidx.compose.runtime.Composable
import com.pietrantuono.movies2023.features.posts.Action
import com.pietrantuono.movies2023.features.posts.UiState
import com.pietrantuono.movies2023.features.posts.UiState.Content

@Composable
fun PostsScreen(state: UiState, actions: (Action) -> Unit = {}) {
    when (state) {
        is Content -> Content(state, actions)
    }
}
