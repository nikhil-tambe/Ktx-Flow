package com.nikhil.slice.db.tweets

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.nikhil.slice.db.BaseDao

@Dao
interface TweetsDao : BaseDao<TweetsEntity> {

    @Query("select * from tweets")
    fun observeAllTweets() : LiveData<List<TweetsEntity>>

}