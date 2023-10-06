package com.pietrantuono.movies2023.features

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pietrantuono.movies2023.features.posts.PostsUiEvent.NavigationPerformed
import com.pietrantuono.movies2023.features.posts.PostsUiState
import com.pietrantuono.movies2023.features.posts.PostsViewModel
import com.pietrantuono.movies2023.features.posts.ui.PostsScreen


private const val NO_NAME = "No name"

@Composable
internal fun MainScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = HOME
    ) {
        composable(route = HOME) {
            val viewModel: PostsViewModel = hiltViewModel()
            val viewState by viewModel.viewState.collectAsStateWithLifecycle(PostsUiState.Content()) //TODO move this
            PostsScreen(viewState) {
                viewModel.accept(it)
            }
            LaunchedEffect(viewState.navDestination) {
                navController.navigateTo(viewState.navDestination)
                viewModel.accept(NavigationPerformed)
            }
        }
        composable(
            route = "$DETAIL/{$ID}",
            arguments = detailNavArguments
        ) {
            val name = it.arguments?.getString(ID)
            Text(text = name ?: NO_NAME)
        }
    }
}

