package com.pietrantuono.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

class PostWithImagesEntity(
    @Embedded val post: PersistedPostEntity,
    @Relation(
        parentColumn = "name",
        entityColumn = "postName",
    )
    val images: List<PersistedImageEntity>
)