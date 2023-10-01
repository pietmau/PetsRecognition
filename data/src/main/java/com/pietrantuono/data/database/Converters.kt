package com.pietrantuono.data.database

import androidx.room.TypeConverter
import com.pietrantuono.data.database.entity.PostWithImagesEntity
import com.pietrantuono.domain.model.reddit.Image
import com.pietrantuono.domain.model.reddit.Post

class Converters { // TODO remove
    @TypeConverter
    fun fromPostWithImagesEntity(value: PostWithImagesEntity?): Post =
        Post(
            key = value?.post?.key ?: 0,
            name = value?.post?.name ?: "",
            title = value?.post?.title ?: "",
            author = value?.post?.author ?: "",
            created = value?.post?.created ?: 0,
            thumbnail = value?.post?.thumbnail ?: "",
            url = value?.post?.url ?: "",
            after = value?.post?.after ?: "",
            before = value?.post?.before ?: "",
            images = value?.images?.map {
                Image(
                    url = it.url,
                    width = it.width,
                    height = it.height
                )
            } ?: emptyList(),
            subredditId = value?.post?.subredditId ?: "",
            id = value?.post?.id ?: "",
            subreddit = value?.post?.subreddit ?: "",
            numComments = value?.post?.numComments ?: 0,
            permalink = value?.post?.permalink ?: "",
            page = value?.post?.page
        )
}