package com.pietrantuono.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.pietrantuono.data.database.entity.PersistedImageEntity
import com.pietrantuono.data.database.entity.PersistedPostEntity
import com.pietrantuono.data.database.entity.PostWithImagesEntity

@Dao
interface RedditDao {

    @Insert
    suspend fun insert(post: PersistedPostEntity)

    @Insert
    suspend fun insert(post: PersistedImageEntity)


    @Transaction
    @Query("SELECT * FROM persistedpostentity where page =:page ORDER BY created DESC LIMIT :limit")
    suspend fun getPosts(page: String? = null, limit: Int? = null): List<PostWithImagesEntity>

    @Delete
    suspend fun delete(post: PersistedPostEntity)

    @Delete
    suspend fun delete(image: PersistedImageEntity)
}