package com.nikhil.slice.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikhil.slice.db.SliceDB.Companion.DB_VERSION
import com.nikhil.slice.db.tweets.TweetsDao
import com.nikhil.slice.db.tweets.TweetsEntity

@Database(
    entities = [TweetsEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class SliceDB : RoomDatabase() {

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "slice_db"
    }

    abstract val tweetsDao: TweetsDao

}