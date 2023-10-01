package com.pietrantuono.movies2023.features.posts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pietrantuono.movies2023.features.posts.ui.PostsScreen

@Composable
internal fun MainScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = HOME
    ) {
        composable(route = HOME) {
            PostsScreen {
                navController.navigateTo(it)
            }
        }
        composable(
            route = "$DETAIL/{$NAME}",
            arguments = detailNavArguments
        ) {
            val name = it.arguments?.getString(NAME)
            Text(text = name ?: "No name")
        }
    }
}

