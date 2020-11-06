package com.nikhil.slice.repositories

import com.nikhil.slice.db.tweets.TweetsDao
import com.nikhil.slice.model.Tweet
import com.nikhil.slice.model.TweetMapper
import com.nikhil.slice.network.TwitterApi
import com.nikhil.slice.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import timber.log.Timber

const val FAKE_PROGRESS = 700L

class TweetsRepository
constructor(
    private val twitterApi: TwitterApi,
    private val tweetsDao: TweetsDao,
    private val mapper: TweetMapper
) : ITweetsRepo {

    var allTweets: List<Tweet> = ArrayList()

    override suspend fun getTweets(): Flow<DataState<List<Tweet>>> = flow {
        emit(DataState.Loading)
        delay(FAKE_PROGRESS) // only to show sync animation

        if (allTweets.isNotEmpty()) {
            emit(DataState.Success(allTweets))
            return@flow
        }

        val tweetsCount = tweetsDao.getAllTweetsCount()
        if (tweetsCount > 0) {
            postAllTweets()
            return@flow
        }

        try {
            val response = twitterApi.getTweets()
            val entityList = response.data.map { mapper.responseToEntity(it) }
            entityList.forEach { tweetsDao.insert(it) }
            postAllTweets()
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    private suspend fun FlowCollector<DataState<List<Tweet>>>.postAllTweets() {
        allTweets = mapper.entityListToModelList(tweetsDao.getAllTweets())
        emit(DataState.Success(allTweets))
    }

    override suspend fun getFilteredList(query: String, animate: Boolean): Flow<DataState<List<Tweet>>> =
        flow {
            if (query.isEmpty()) {
                emit(DataState.Success(allTweets))
                return@flow
            }

            Timber.d("New Text: $query")
            if (animate) {
                emit(DataState.Loading)
                delay(FAKE_PROGRESS) // only to show sync animation
            }
            val trimmed = query.trim()
            val filteredList = allTweets.filter { data ->
                data.name.contains(trimmed, true)
                        || data.handle.contains(trimmed, true)
                        || data.tweetText.contains(trimmed, true)
            }
            emit(DataState.Success(filteredList))
        }

}