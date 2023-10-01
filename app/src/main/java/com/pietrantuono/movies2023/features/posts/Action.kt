package com.pietrantuono.movies2023.features.posts

sealed class Action {

    object GetInitialPosts : Action()

    data class GetNextPosts(val indexOfLastItem: Int) : Action()
}