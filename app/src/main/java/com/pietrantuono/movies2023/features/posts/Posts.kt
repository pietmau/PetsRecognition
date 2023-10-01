package com.pietrantuono.movies2023.features.posts

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PostsScreen(state: UiState, actions: (Action) -> Unit = {}) {

    LazyColumn {
        items((state as UiState.Content).data) { post ->
            Text(
                text = post.title ?: "", // TODO
                modifier = Modifier.padding(16.dp),
            )
        }
    }

    LaunchedEffect(true) {
        actions(Action.GetInitialPosts)
    }
}