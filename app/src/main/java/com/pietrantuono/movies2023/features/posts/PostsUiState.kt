package com.pietrantuono.movies2023.features.posts

import com.pietrantuono.domain.model.reddit.Post
import com.pietrantuono.movies2023.features.Destination
import com.pietrantuono.movies2023.features.Destination.None

sealed class PostsUiState {
    abstract val navDestination: Destination

    data class Content(val posts: List<Post> = emptyList(), override val navDestination: Destination = None) : PostsUiState()
}