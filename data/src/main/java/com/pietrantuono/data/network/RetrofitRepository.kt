package com.pietrantuono.data.network

import com.pietrantuono.data.network.api.api.reddit.RedditApiClient
import com.pietrantuono.domain.RedditRepository
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    private val client: RedditApiClient, private val mapper: NetworkDataMapper
) : RedditRepository {
    override suspend fun getPosts(before: String?, after: String?, limit: Int?): List<Post> = try {
        val response = client.getSubReddit(MEMES, limit, before, after, null)
        response.data
            ?.posts
            ?.filter { it.data != null }
            ?.map { mapper.map(it.data!!).copy(kind = it.kind) }
            ?: emptyList()
    } catch (e: Exception) {
        throw e // TODO
    }

    private companion object {
        private const val MEMES = "memes"
    }
}