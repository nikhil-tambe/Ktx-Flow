package com.nikhil.slice.ui.main

import com.nikhil.slice.db.tweets.TweetsDao
import com.nikhil.slice.model.Tweet
import com.nikhil.slice.model.TweetMapper
import com.nikhil.slice.network.TwitterApi
import com.nikhil.slice.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val twitterApi: TwitterApi,
    private val tweetsDao: TweetsDao,
    private val mapper: TweetMapper
) {

    suspend fun getTweets(): Flow<DataState<List<Tweet>>> = flow {
        emit(DataState.Loading)
        delay(700) // only to show sync animation

        val allTweetFromDB = tweetsDao.getAllTweets()
        if (allTweetFromDB.isNotEmpty()) {
            val allTweets = mapper.entityListToModelList(allTweetFromDB)
            emit(DataState.Success(allTweets))
            return@flow
        }

        try {
            val response = twitterApi.getTweets()
            val entityList = response.data.map { mapper.responseToEntity(it) }
            entityList.forEach { tweetsDao.insert(it) }
            val allTweets = mapper.entityListToModelList(tweetsDao.getAllTweets())
            emit(DataState.Success(allTweets))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}