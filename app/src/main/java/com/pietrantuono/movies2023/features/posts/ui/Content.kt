package com.pietrantuono.movies2023.features.posts.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pietrantuono.movies2023.features.posts.PostsAction
import com.pietrantuono.movies2023.features.posts.PostsAction.PostClicked
import com.pietrantuono.movies2023.features.posts.PostsViewState.Content

@Composable
internal fun Content(state: Content = Content(), actions: (PostsAction) -> Unit = {}) {
    val listState = rememberLazyListState()
    val shouldPaginate by listState.shouldPaginate()

    LazyColumn(state = listState) {
        items(items = state.posts, // TODO, add error and laoding
            key = { it.name }) { post ->
            RedditCard(
                modifier = Modifier.clickable { actions(PostClicked(post)) },
                post = post
            )
        }
    }
    Spacer(modifier = Modifier.padding(top = 4.dp)) // TODO Externzalizex
    LaunchedEffect(true) {
        actions(PostsAction.GetInitialPosts)
    }
    LaunchedEffect(shouldPaginate) {
        if (shouldPaginate) {
            val indexOfLastItem = listState.layoutInfo.totalItemsCount - 1
            actions(PostsAction.GetNextPosts(indexOfLastItem))
        }
    }
}
