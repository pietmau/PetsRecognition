package com.pietrantuono.data.database

import com.pietrantuono.data.database.entity.PersistedPostEntity
import com.pietrantuono.domain.Mapper
import com.pietrantuono.domain.model.reddit.Post
import javax.inject.Inject

class PostToPersistedPostEntityMapper @Inject constructor() : Mapper<Post, PersistedPostEntity> {
    override fun map(input: Post) =
        PersistedPostEntity(
            name = input.name,
            title = input.title,
            author = input.author,
            created = input.created,
            thumbnail = input.thumbnail,
            url = input.url,
            score = input.score,
            permalink = input.permalink,
            kind = input.kind,
            subreddit = input.subreddit,
            subredditId = input.subredditId,
            id = input.id,
            numComments = input.numComments,
            ups = input.ups,
            before = input.before,
            after = input.after
        )
}
