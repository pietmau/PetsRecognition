package com.pietrantuono.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.pietrantuono.data.database.entity.PersistedImageEntity
import com.pietrantuono.data.database.entity.PersistedPostEntity
import com.pietrantuono.data.database.entity.PostWithImagesEntity
import com.pietrantuono.domain.model.reddit.Post

@Dao
interface RedditDao {

    @Insert
    fun insert(post: PersistedPostEntity)

    @Insert
    fun insert(post: PersistedImageEntity)

    @Transaction
    @Query("SELECT * FROM persistedpostentity WHERE score < :postScore ORDER BY score LIMIT :limit")
    fun getBefore(postScore: Int, limit: Int): List<PostWithImagesEntity>

    @Transaction
    @Query("SELECT * FROM persistedpostentity WHERE score > :postScore ORDER BY score LIMIT :limit")
    fun getAfter(postScore: Int, limit: Int): List<PostWithImagesEntity>

    @Delete
    fun delete(post: PersistedPostEntity)

    @Delete
    fun delete(image: PersistedImageEntity)
}