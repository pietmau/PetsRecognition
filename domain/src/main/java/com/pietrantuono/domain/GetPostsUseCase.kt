package com.pietrantuono.domain

import com.pietrantuono.domain.GetPostsUseCase.Params
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: RedditRepository) : UseCase<Params, List<Post>> { // TODO remove

    override suspend fun execute(params: Params) = repository.getPosts(limit = params.limit, before = params.before, after = params.after)

    data class Params(val before: String? = null, val after: String? = null, val limit: Int? = null)
}