package com.pietrantuono.movies2023.features.posts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pietrantuono.movies2023.features.posts.Action.NavigationPerformed
import com.pietrantuono.movies2023.features.posts.UiState.Content
import com.pietrantuono.movies2023.features.posts.ui.PostsScreen

@Composable
internal fun MainScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HOME
    ) {
        composable(HOME) {
            val viewModel = hiltViewModel<MainViewModel>()
            val viewState by viewModel.uiState.collectAsStateWithLifecycle(Content())
            PostsScreen(viewState) { action ->
                viewModel.accept(action)
            }
            LaunchedEffect(viewState.navDestination) {
                navController.navigate(viewState.navDestination)
                viewModel.accept(NavigationPerformed)
            }
        }
        composable(DETAIL) {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = Color.Red
            ) {

            }
        }
    }
}
