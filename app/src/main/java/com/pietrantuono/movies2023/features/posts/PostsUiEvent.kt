package com.pietrantuono.movies2023.features.posts

import com.pietrantuono.domain.model.reddit.Post

sealed class PostsUiEvent {

    object GetInitialPosts : PostsUiEvent()

    data class GetNextPosts(val indexOfLastItem: Int) : PostsUiEvent()

    data class PostClicked(val post: Post) : PostsUiEvent()

    object NavigationPerformed : PostsUiEvent()
}