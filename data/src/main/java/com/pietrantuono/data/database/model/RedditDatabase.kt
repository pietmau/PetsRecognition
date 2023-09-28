package com.pietrantuono.data.database.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pietrantuono.data.database.RedditDao

@Database(entities = [PersistedPost::class], version = 1)
abstract class RedditDatabase : RoomDatabase() {
    abstract fun userDao(): RedditDao
}