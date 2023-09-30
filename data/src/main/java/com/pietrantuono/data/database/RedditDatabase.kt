package com.pietrantuono.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pietrantuono.data.database.model.PersistedImage
import com.pietrantuono.data.database.model.PersistedPost

@Database(entities = [PersistedPost::class, PersistedImage::class], version = 1, exportSchema = false)
abstract class RedditDatabase : RoomDatabase() {
    abstract fun userDao(): RedditDao
}