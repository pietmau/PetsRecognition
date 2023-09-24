package com.pietrantuono.movies2023.features.posts

import androidx.lifecycle.ViewModel
import com.pietrantuono.domain.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val useCase: GetPostsUseCase) : ViewModel() { // TODO use generics
}