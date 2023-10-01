package com.pietrantuono.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.pietrantuono.data.database.entity.PersistedImageEntity
import com.pietrantuono.data.database.entity.PersistedPostEntity
import com.pietrantuono.data.database.entity.PostWithImagesEntity

@Dao
interface RedditDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(post: PersistedPostEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(post: PersistedImageEntity)


//    @Transaction
//    @Query("SELECT * FROM persistedpostentity WHERE page = :page ORDER BY created DESC")
//    suspend fun getPosts(page: String): List<PostWithImagesEntity>

    @Transaction
    @Query("SELECT * FROM persistedpostentity WHERE `key` > :index ORDER BY created DESC LIMIT :limit")
    suspend fun getPostsAfter(index: Long, limit: Int): List<PostWithImagesEntity>

    @Transaction
    @Query("SELECT * FROM persistedpostentity  ORDER BY created DESC ")
    suspend fun getLatestPosts(): List<PostWithImagesEntity>

    @Delete
    suspend fun delete(post: PersistedPostEntity)

    @Delete
    suspend fun delete(image: PersistedImageEntity)
}