package com.pietrantuono.data.database

import com.pietrantuono.data.database.entity.PersistedImageEntity
import com.pietrantuono.domain.Mapper
import com.pietrantuono.domain.model.reddit.Image

class ImageMapper : Mapper<Pair<String, Image>, PersistedImageEntity> {
    override fun map(input: Pair<String, Image>) =
        PersistedImageEntity(
            url = input.second.url,
            width = input.second.width,
            height = input.second.height,
            postName = input.first
        )
}
