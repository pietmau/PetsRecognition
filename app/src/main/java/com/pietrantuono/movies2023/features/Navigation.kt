package com.pietrantuono.movies2023.features

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.pietrantuono.movies2023.features.Destination.Detail

internal const val HOME = "home"
internal const val ID = "id"
internal const val DETAIL = "detail"

internal fun NavHostController.navigateTo(destination: Destination) {
    when (destination) {
        is Detail -> navigate("$DETAIL/${destination.name}")
        else -> Unit
    }
}

sealed class Destination {
    object None : Destination()
    object Home : Destination()
    data class Detail(val name: String) : Destination()
}

internal val detailNavArguments
    get() = listOf(navArgument(ID) { type = NavType.StringType })