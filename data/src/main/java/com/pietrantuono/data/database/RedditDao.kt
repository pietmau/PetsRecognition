package com.pietrantuono.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pietrantuono.data.database.model.PersistedPost

@Dao
interface RedditDao {
    @Query("SELECT * FROM persistedpost")
    fun getAll(): List<PersistedPost>

    @Insert
    fun insertAll(vararg posts: PersistedPost)

    @Query("SELECT * FROM persistedpost WHERE score < :postScore ORDER BY score LIMIT :limit")
    fun getBefore(postScore: Int, limit: Int): List<PersistedPost>

    @Query("SELECT * FROM persistedpost WHERE score > :postScore ORDER BY score LIMIT :limit")
    fun getAfter(postScore: Int, limit: Int): List<PersistedPost>

    @Delete
    fun delete(post: PersistedPost)
}