package com.nikhil.slice.ui.main

import com.nikhil.slice.db.tweets.TweetsDao
import com.nikhil.slice.model.Tweet
import com.nikhil.slice.model.TweetMapper
import com.nikhil.slice.network.TwitterApi
import com.nikhil.slice.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

const val FAKE_PROGRESS = 700L

class MainRepository
constructor(
    private val twitterApi: TwitterApi,
    private val tweetsDao: TweetsDao,
    private val mapper: TweetMapper
) {

    var allTweets: List<Tweet> = ArrayList()

    suspend fun getTweets(): Flow<DataState<List<Tweet>>> = flow {
        emit(DataState.Loading)
        delay(FAKE_PROGRESS) // only to show sync animation

        if (allTweets.isNotEmpty()) {
            emit(DataState.Success(allTweets))
            return@flow
        }

        val allTweetFromDB = tweetsDao.getAllTweets()
        if (allTweetFromDB.isNotEmpty()) {
            allTweets = mapper.entityListToModelList(allTweetFromDB)
            emit(DataState.Success(allTweets))
            return@flow
        }

        try {
            val response = twitterApi.getTweets()
            val entityList = response.data.map { mapper.responseToEntity(it) }
            entityList.forEach { tweetsDao.insert(it) }
            allTweets = mapper.entityListToModelList(tweetsDao.getAllTweets())
            emit(DataState.Success(allTweets))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getFilteredList(query: String): Flow<DataState<List<Tweet>>> = flow {
        if (query.isEmpty()) {
            emit(DataState.Success(allTweets))
            return@flow
        }

        Timber.d("New Text: $query")
        emit(DataState.Loading)
        delay(FAKE_PROGRESS) // only to show sync animation

        val trimmed = query.trim()
        val filteredList = allTweets.filter { data ->
            data.name.contains(trimmed, true)
                    || data.handle.contains(trimmed, true)
                    || data.tweetText.contains(trimmed, true)
        }
        emit(DataState.Success(filteredList))
    }

}