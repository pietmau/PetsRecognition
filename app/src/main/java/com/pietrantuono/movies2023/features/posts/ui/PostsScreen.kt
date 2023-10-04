package com.pietrantuono.movies2023.features.posts.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pietrantuono.movies2023.features.posts.PostsUiEvent
import com.pietrantuono.movies2023.features.Destination
import com.pietrantuono.movies2023.features.posts.PostsViewModel
import com.pietrantuono.movies2023.features.posts.PostsUiState.Content

@Composable
fun PostsScreen(viewModel: PostsViewModel = hiltViewModel(), navigateTo: (Destination) -> Unit = {}) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle(Content())
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
