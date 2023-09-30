package com.pietrantuono.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.pietrantuono.data.database.model.PersistedImage
import com.pietrantuono.data.database.model.PersistedPost
import com.pietrantuono.data.database.model.PostWithImages

@Dao
interface RedditDao {
    @Transaction
    @Query("SELECT * FROM persistedpost")
    fun getAll(): List<PostWithImages>

    @Transaction
    @Insert
    fun insertAll(vararg posts: PostWithImages){
        posts.forEach {
            insert(it.post)
            it.images.forEach { image ->
                insert(image)
            }
        }
    }

    @Insert
    fun insert(post: PersistedPost)

    @Insert
    fun insert(post: PersistedImage)

    @Transaction
    @Query("SELECT * FROM persistedpost WHERE score < :postScore ORDER BY score LIMIT :limit")
    fun getBefore(postScore: Int, limit: Int): List<PostWithImages>

    @Transaction
    @Query("SELECT * FROM persistedpost WHERE score > :postScore ORDER BY score LIMIT :limit")
    fun getAfter(postScore: Int, limit: Int): List<PostWithImages>

    @Transaction
    @Delete
    fun delete(post: PersistedPost)
}