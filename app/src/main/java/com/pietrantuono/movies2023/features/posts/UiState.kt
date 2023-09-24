package com.pietrantuono.movies2023.features.posts

sealed class UiState {
    data class Content(val data: List<String> = emptyList()) : UiState()
}