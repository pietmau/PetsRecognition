package com.pietrantuono.data.network

import com.pietrantuono.data.network.entity.reddit.NetworkPostEntity
import com.pietrantuono.domain.Mapper
import com.pietrantuono.domain.model.reddit.Image
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class NetworkDataMapper @Inject constructor() : Mapper<NetworkPostEntity, Post> {
    override suspend fun map(input: Pair<String?, Any?>): Post =
        Post(kind = input.kind,
            name = input.data?.name,
            title = input.data?.title,
            author = input.data?.author,
            created = input.data?.created,
            thumbnail = input.data?.thumbnail,
            url = input.data?.url,
            score = input.data?.score,
            numComments = input.data?.numComments,
            permalink = input.data?.permalink,
            subreddit = input.data?.subreddit,
            subredditId = input.data?.subredditId,
            id = input.data?.id,
            ups = input.data?.ups,
            images = input.data?.preview?.images?.flatMap { it.resolutions }?.map {
                Image(
                    url = it.url,
                    width = it.width,
                    height = it.height
                )
            } ?: emptyList())

}