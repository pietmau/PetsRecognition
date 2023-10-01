package com.pietrantuono.movies2023.features.posts

import com.pietrantuono.domain.model.reddit.Post

sealed class Action {

    object GetInitialPosts : Action()

    data class GetNextPosts(val indexOfLastItem: Int) : Action()

    data class PostClicked(val post: Post) : Action()

    object NavigationPerformed : Action()
}