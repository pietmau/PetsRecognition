package com.pietrantuono.movies2023.features.posts

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.pietrantuono.movies2023.features.posts.Destination.Detail
import com.pietrantuono.movies2023.features.posts.Destination.Home
import com.pietrantuono.movies2023.features.posts.Destination.None

internal const val HOME = "home"
internal const val NAME = "name"
internal const val DETAIL = "detail"

internal fun NavHostController.navigate(destination: Destination) {
    when (destination) {
        is None -> Unit
        is Home -> Unit
        is Detail -> navigate("$DETAIL/${destination.name}")
    }
}

sealed class Destination {
    object None : Destination()
    object Home : Destination()
    data class Detail(val name: String) : Destination()
}

internal val detailNavArguments
    get() = listOf(navArgument(NAME) { type = NavType.StringType })