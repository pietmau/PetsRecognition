package com.pietrantuono.movies2023.features.posts.ui

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pietrantuono.movies2023.features.posts.Action
import com.pietrantuono.movies2023.features.posts.Action.GetInitialPosts
import com.pietrantuono.movies2023.features.posts.UiState

@Composable
fun PostsScreen(state: UiState, actions: (Action) -> Unit = {}) {
    Log.e("PostsScreen", "Composing")
    val lazyColumnListState: LazyListState = rememberLazyListState()
    val shouldPaginate by remember { derivedStateOf { lazyColumnListState.shouldPaginate() } }

    LazyColumn(state = lazyColumnListState) {
        items((state as UiState.Content).data) { post ->
            Text(
                text = post.title ?: "", // TODO
                modifier = Modifier.padding(16.dp),
            )
        }
    }
    LaunchedEffect(true) {
        actions(GetInitialPosts)
    }
    LaunchedEffect(shouldPaginate) {
        Log.e("PostsScreen", "shouldPaginate $shouldPaginate")
    }
}

