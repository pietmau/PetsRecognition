package com.pietrantuono.movies2023.features.posts

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.pietrantuono.domain.model.reddit.Post

@Composable
fun PostsScreen(state: LazyPagingItems<Post>, actions: (Action) -> Unit = {}) {
    LazyColumn {
        items(count = state.itemCount,
            key = state.itemKey { it.name }) { position ->
            Text(
                modifier = Modifier.padding(48.dp),
                text = state[position]?.title ?: ""
            )
        }
    }

    LaunchedEffect(true) {
        actions(Action.GetPosts)
    }
}