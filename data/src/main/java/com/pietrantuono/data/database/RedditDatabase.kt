package com.pietrantuono.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pietrantuono.data.database.entity.PersistedImageEntity
import com.pietrantuono.data.database.entity.PersistedPostEntity

@Database(entities = [PersistedPostEntity::class, PersistedImageEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RedditDatabase : RoomDatabase() {
    abstract fun redditDao(): RedditDao
}