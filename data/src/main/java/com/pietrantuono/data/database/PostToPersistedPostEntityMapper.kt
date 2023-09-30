package com.pietrantuono.data.database

import com.pietrantuono.data.database.entity.PersistedPostEntity
import com.pietrantuono.domain.Mapper
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class PostToPersistedPostEntityMapper @Inject constructor() : Mapper<Pair<String?, Post>, PersistedPostEntity> {
    override fun map(input: Pair<String?, Post>): PersistedPostEntity {
        val (key, post) = input
        return PersistedPostEntity(
            name = post.name,
            title = post.title,
            author = post.author,
            created = post.created,
            thumbnail = post.thumbnail,
            url = post.url,
            score = post.score,
            permalink = post.permalink,
            kind = post.kind,
            subreddit = post.subreddit,
            subredditId = post.subredditId,
            id = post.id,
            numComments = post.numComments,
            ups = post.ups,
            before = post.before,
            after = post.after,
            page = key
        )
    }
}
