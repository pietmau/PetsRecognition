package com.pietrantuono.movies2023.features.posts.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pietrantuono.movies2023.features.posts.Action
import com.pietrantuono.movies2023.features.posts.UiState.Content

@Composable
internal fun Content(state: Content, actions: (Action) -> Unit = {}) {
    val listState = rememberLazyListState()
    val shouldPaginate by listState.rememberShouldPaginate()

    LazyColumn(state = listState) {
        items(items = state.posts, // TODO, add error and laoding
            key = { it.name }) { post ->
            Card(modifier = Modifier.padding(top = 4.dp, start = 4.dp, end = 4.dp)) {//TODO Externzalize
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = post.title ?: "", // TODO add better default
                        modifier = Modifier.padding(16.dp), //TODO Externzalize
                        style = MaterialTheme.typography.titleSmall
                    )
                }

            }
        }
    }
    Spacer(modifier = Modifier.padding(top = 4.dp)) // TODO Externzalizex
    LaunchedEffect(true) {
        actions(Action.GetInitialPosts)
    }
    LaunchedEffect(shouldPaginate) {
        if (shouldPaginate) {
            val indexOfLastItem = listState.layoutInfo.totalItemsCount - 1
            actions(Action.GetNextPosts(indexOfLastItem))
        }
    }
}

