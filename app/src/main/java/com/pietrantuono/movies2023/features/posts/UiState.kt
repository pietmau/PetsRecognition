package com.pietrantuono.movies2023.features.posts

import com.pietrantuono.domain.model.reddit.Post

sealed class UiState {
    data class Content(val data: List<Post> = emptyList()) : UiState()
}