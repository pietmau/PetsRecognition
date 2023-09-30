package com.pietrantuono.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersistedImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "url")
    val url: String? = null,
    @ColumnInfo(name = "width")
    val width: Int? = null,
    @ColumnInfo(name = "height")
    val height: Int? = null,
    @ColumnInfo(name = "postName")
    val postName: String? = null,
)