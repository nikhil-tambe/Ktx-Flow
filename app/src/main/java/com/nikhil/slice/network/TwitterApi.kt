package com.nikhil.slice.network

import com.nikhil.slice.network.response.ResponseTweet
import retrofit2.http.GET

const val TWITTER_BASE_URL = "https://6f8a2fec-1605-4dc7-a081-a8521fad389a.mock.pstmn.io/"

interface TwitterApi {

    @GET("tweets")
    suspend fun getTweets() : ResponseTweet

}