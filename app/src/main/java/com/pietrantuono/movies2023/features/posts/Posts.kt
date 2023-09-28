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

@Composable
fun PostsScreen(state: LazyPagingItems<Pair<String, String>>, actions: (Action) -> Unit = {}) {
    LazyColumn {
        items(count = state.itemCount,
            key = state.itemKey { it.second }) { position ->
            Text(
                modifier = Modifier.padding(48.dp),
                text = state[position]!!.first!!
            )
        }
    }

    LaunchedEffect(true) {
        actions(Action.GetPosts)
    }
}