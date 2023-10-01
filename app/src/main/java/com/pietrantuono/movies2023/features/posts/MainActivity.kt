package com.pietrantuono.movies2023.features.posts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.pietrantuono.movies2023.features.posts.ui.theme.Movies2023Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Movies2023Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = rememberNavController(), startDestination = "home") {
                        composable("home") {
                            val viewModel: PostsViewModel = hiltViewModel()
                            val viewSate by viewModel.uiState.collectAsStateWithLifecycle(UiState.Content())
//                            val items = viewModel.items.collectAsLazyPagingItems()
//                            PostsScreen(items)
                        }
                    }
                }
            }
        }
    }
}
