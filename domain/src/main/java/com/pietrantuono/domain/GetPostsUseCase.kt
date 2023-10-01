package com.pietrantuono.domain

import com.pietrantuono.domain.GetPostsUseCase.Params
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: RedditRepository) : UseCase<Params, List<Post>> { // TODO remove

    override suspend fun execute(params: Params) =
        when (params) {
            is Params.Initial -> repository.getLatestPosts()
            is Params.Next -> repository.getNextPosts(params.index, params.page, repository.defaultPageSize)
            is Params.Previous -> TODO()
        }

    sealed class Params {
        object Initial : Params()
        data class Next(val index: Long, val page: String) : Params()
        data class Previous(val before: String) : Params()
    }
}