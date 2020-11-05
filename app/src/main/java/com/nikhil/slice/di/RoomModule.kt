package com.nikhil.slice.di

import android.content.Context
import androidx.room.Room
import com.nikhil.slice.db.SliceDB
import com.nikhil.slice.db.tweets.TweetsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideSliceDB(
        @ApplicationContext context: Context
    ): SliceDB {
        return Room.databaseBuilder(context, SliceDB::class.java, SliceDB.DB_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideTweetsDao(sliceDB: SliceDB): TweetsDao {
        return sliceDB.tweetsDao
    }

}