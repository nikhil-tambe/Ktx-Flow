package com.nikhil.slice.db.tweets

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tweets")
data class TweetsEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "handle")
    var handle: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "retweetCount")
    var retweetCount: Int,

    @ColumnInfo(name = "favCount")
    var favCount: Int,

    @ColumnInfo(name = "tweetText")
    var tweetText: String

)