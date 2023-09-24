package com.pietrantuono.data

import com.pietrantuono.data.network.api.api.reddit.RedditApiClient
import com.pietrantuono.data.network.model.reddit.NetworkData
import com.pietrantuono.domain.GetPostsRepository
import com.pietrantuono.domain.model.reddit.Post
import com.pietrantuono.domain.model.reddit.Posts
import javax.inject.Inject

class RetrofitRoomGetPostsRepository @Inject constructor(
    private val client: RedditApiClient
) : GetPostsRepository {
    override suspend fun getPosts(before: String?, after: String?, limit: Int?): Posts {
        try {
            val response = client.getSubReddit(MEMES, limit, before, after, null)

            return Posts(
                posts = response.data?.posts?.map {
                    it.data!!.toPost() // TODO FIX!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                } ?: emptyList(),
                before = response.data?.before,
                after = response.data?.after
            )
        } catch (e: Exception) {
            throw e // TODO FIX!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }
    }

    private companion object {
        private const val MEMES = "r/memes/top/?t=all&count=555"
    }

    private fun NetworkData.toPost(): Post = Post(
        id = id,
        title = title,
        author = author,
        created = created,
        thumbnail = thumbnail,
        url = url,
        comments = comments,
        score = score,
        permalink = permalink,
        preview = preview?.images?.firstOrNull()?.source?.url
    )
}