package com.pietrantuono.data.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.pietrantuono.domain.model.reddit.Image

class PostWithImages(
    @Embedded val post: PersistedPost,
    @Relation(
        parentColumn = "name",
        entityColumn = "postName",
    )
    val images: List<PersistedImage>
)