package com.nikhil.slice.network.response

import com.google.gson.annotations.SerializedName

data class ResponseTweet(

    @SerializedName("success")
    val success: Boolean,

    @SerializedName("data")
    val data: List<ResponseTweetData>

)

data class ResponseTweetData (

    @SerializedName("name")
    val name: String,

    @SerializedName("handle")
    val handle: String,

    @SerializedName("profileImageUrl")
    val image: String,

    @SerializedName("retweetCount")
    val retweetCount: Int,

    @SerializedName("favoriteCount")
    val favCount: Int,

    @SerializedName("text")
    val tweetText: String
)