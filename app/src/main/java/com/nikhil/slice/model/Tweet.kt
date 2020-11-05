package com.nikhil.slice.model

data class Tweet(
    val name: String,
    val handle: String,
    val image: String,
    val retweetCount: Int,
    val favCount: Int,
    val tweetText: String,
    val timestamp: Long
)