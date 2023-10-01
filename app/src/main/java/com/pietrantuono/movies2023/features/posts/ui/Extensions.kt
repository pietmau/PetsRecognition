package com.pietrantuono.movies2023.features.posts.ui

import androidx.compose.foundation.lazy.LazyListState

private const val OFFSET = 5

internal fun LazyListState.shouldPaginate(): Boolean {
    val totalItemsCount = layoutInfo.totalItemsCount
    val indexOfLastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: return false
    return totalItemsCount - indexOfLastVisibleItem < OFFSET
}