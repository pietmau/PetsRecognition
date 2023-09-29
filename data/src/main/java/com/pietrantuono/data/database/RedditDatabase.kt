package com.pietrantuono.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pietrantuono.data.database.model.PersistedPost

@Database(entities = [PersistedPost::class], version = 1)
abstract class RedditDatabase : RoomDatabase() {
    abstract fun userDao(): RedditDao
}