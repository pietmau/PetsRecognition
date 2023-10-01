package com.pietrantuono.movies2023.features.posts

import androidx.navigation.NavHostController

internal const val HOME = "home"
internal const val DETAIL = "detail"

internal fun NavHostController.navigate(destination: Destination) {
    when (destination) {
        Destination.NONE -> Unit
        Destination.HOME -> Unit
        Destination.DETAIL -> navigate(DETAIL)
    }
}

enum class Destination {
    NONE, HOME, DETAIL
}