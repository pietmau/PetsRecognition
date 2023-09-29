package com.pietrantuono.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersistedPost(
    @PrimaryKey
    val name: String? = null,
    @ColumnInfo(name = "score")
    val score: Int? = null,
    @ColumnInfo(name = "kind")
    val kind: String? = null,
    @ColumnInfo(name = "subreddit")
    val subreddit: String? = null,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "ups")
    val ups: Int? = null,
    @ColumnInfo(name = "created")
    val created: Int? = null,
    @ColumnInfo(name = "subreddit_id")
    val subredditId: String? = null,
    @ColumnInfo(name = "id")
    val id: String? = null,
    @ColumnInfo(name = "author")
    val author: String? = null,
    @ColumnInfo(name = "num_comments")
    val numComments: Int? = null,
    @ColumnInfo(name = "permalink")
    val permalink: String? = null,
    @ColumnInfo(name = "url")
    val url: String? = null
)