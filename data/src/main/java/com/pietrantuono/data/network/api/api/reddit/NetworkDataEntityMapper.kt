package com.pietrantuono.data.network.api.api.reddit

import com.pietrantuono.data.network.entity.reddit.NetworkDataEntity
import com.pietrantuono.domain.Mapper
import com.pietrantuono.domain.model.reddit.Post

class NetworkDataEntityMapper : Mapper<Pair<String?, NetworkDataEntity>, Post> {
    override suspend fun map(input: Pair<String?, NetworkDataEntity>): Post {
        val (key, networkDataEntity) = input
        return Post(
            kind = key,
            id = networkDataEntity.id,
            name = networkDataEntity.name,
            title = networkDataEntity.title,
            author = networkDataEntity.author,
            created = networkDataEntity.created,
            thumbnail = networkDataEntity.thumbnail,
            numComments = networkDataEntity.numComments,
            url = networkDataEntity.url,
            permalink = networkDataEntity.permalink
        )
    }
}
