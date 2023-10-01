package com.pietrantuono.movies2023.features.posts.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pietrantuono.movies2023.features.posts.PostsAction
import com.pietrantuono.movies2023.features.posts.Destination
import com.pietrantuono.movies2023.features.posts.MainViewModel
import com.pietrantuono.movies2023.features.posts.PostsViewState.Content

@Composable
fun PostsScreen(viewModel: MainViewModel = hiltViewModel(), navigateTo: (Destination) -> Unit = {}) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle(Content())
    when (val state = viewState) {
        is Content -> Content(state) {
            viewModel.accept(it)
        }
    }
    LaunchedEffect(viewState.navDestination) {
        navigateTo(viewState.navDestination)
        viewModel.accept(PostsAction.NavigationPerformed)
    }
}
