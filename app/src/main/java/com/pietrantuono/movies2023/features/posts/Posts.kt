package com.pietrantuono.movies2023.features.posts

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PostsScreen(viewModel: PostsViewModel = hiltViewModel()) {
    val viewSate by viewModel.uiState.collectAsStateWithLifecycle(UiState.Content())
    LazyColumn {
        val count: List<String> = (viewSate as UiState.Content).data
        items(count) { post ->
            Text(
                modifier = Modifier.padding(48.dp),
                text = post
            )
        }
    }

    LaunchedEffect(true) {
        viewModel.accept(Action.GetPosts)
    }
}