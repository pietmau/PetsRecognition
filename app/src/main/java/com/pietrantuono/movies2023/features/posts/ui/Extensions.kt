package com.pietrantuono.movies2023.features.posts.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

private const val OFFSET = 5

@Composable
internal fun LazyListState.rememberShouldPaginate() = remember {
    derivedStateOf {
        val totalItemsCount = layoutInfo.totalItemsCount
        val indexOfLastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: return@derivedStateOf false
        return@derivedStateOf totalItemsCount - indexOfLastVisibleItem < OFFSET
    }
}