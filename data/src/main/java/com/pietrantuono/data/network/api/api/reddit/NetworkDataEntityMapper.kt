package com.pietrantuono.data.network.api.api.reddit

import com.pietrantuono.data.network.entity.reddit.NetowrkRedditResponseEntity
import com.pietrantuono.domain.Mapper
import com.pietrantuono.domain.model.reddit.Image
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class NetworkDataEntityMapper @Inject constructor() : Mapper<NetowrkRedditResponseEntity, List<Post>> {
    override fun map(input: NetowrkRedditResponseEntity): List<Post> {
        val posts = input.data?.posts
        val before = input.data?.before
        val after = input.data?.after
        return posts?.filter { it.data != null }?.map { (kind, data) ->
            Post(
                kind = kind,
                name = data!!.name,
                subreddit = data.subreddit,
                thumbnail = data.thumbnail,
                title = data.title,
                ups = data.ups,
                created = data.created,
                images = data.preview?.images?.flatMap { it.resolutions }?.map {
                    Image(
                        url = it.url,
                        width = it.width,
                        height = it.height
                    )
                } ?: emptyList(),
                subredditId = data.subredditId,
                id = data.id,
                author = data.author,
                numComments = data.numComments,
                permalink = data.permalink,
                url = data.url,
                score = data.score,
                before = before,
                after = after
            )
        } ?: emptyList()
    }
}

