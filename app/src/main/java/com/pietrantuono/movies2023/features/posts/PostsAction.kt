package com.pietrantuono.movies2023.features.posts

import com.pietrantuono.domain.model.reddit.Post

sealed class PostsAction {

    object GetInitialPosts : PostsAction()

    data class GetNextPosts(val indexOfLastItem: Int) : PostsAction()

    data class PostClicked(val post: Post) : PostsAction()

    object NavigationPerformed : PostsAction()
}