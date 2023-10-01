package com.pietrantuono.data.database

import com.pietrantuono.data.database.entity.PostWithImagesEntity
import com.pietrantuono.domain.Mapper
import com.pietrantuono.domain.model.reddit.Image
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class PostWithImagesEntityToPostMapper @Inject constructor() : Mapper<PostWithImagesEntity, Post> {
    override fun map(input: PostWithImagesEntity) =
        Post(
            key= input.post.key,
            name = input.post.name,
            title = input.post.title,
            author = input.post.author,
            created = input.post.created,
            thumbnail = input.post.thumbnail,
            url = input.post.url,
            score = input.post.score,
            permalink = input.post.permalink,
            kind = input.post.kind,
            subreddit = input.post.subreddit,
            subredditId = input.post.subredditId,
            id = input.post.id,
            numComments = input.post.numComments,
            ups = input.post.ups,
            before = input.post.before,
            after = input.post.after,
            images = input.images.map {
                Image(
                    url = it.url,
                    width = it.width,
                    height = it.height
                )
            },
            page = input.post.page
        )
}
