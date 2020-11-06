package com.nikhil.slice.repositories

import com.nikhil.slice.model.Tweet
import com.nikhil.slice.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * This class is only to test our ViewModel
 * and NOT to test the actual repository
 * */
class FakeTweetsRepo : ITweetsRepo {

    private val t1 = Tweet("Nikhil", "@nikhil", "img_url", 123, 123, "First Tweet", 1)
    private val t2 = Tweet("Rick", "@rick", "img_url", 221, 101, "Second Tweet", 2)
    private val t3 = Tweet("Morty", "@morty", "img_url", 321, 100, "Third Tweet", 3)
    private val allTweets = mutableListOf<Tweet>()

    private fun createTweetList() {
        allTweets.add(t1)
        allTweets.add(t2)
        allTweets.add(t3)
    }

    override suspend fun getTweets(): Flow<DataState<List<Tweet>>> = flow {
        createTweetList()
        emit(DataState.Success(allTweets))
    }

    override suspend fun getFilteredList(
        query: String,
        animate: Boolean
    ): Flow<DataState<List<Tweet>>> = flow {
        createTweetList()
        val trimmedQuery = query.trim()
        val filteredList = allTweets.filter {
            it.name.contains(trimmedQuery)
                    || it.handle.contains(trimmedQuery)
                    || it.tweetText.contains(trimmedQuery)
        }
        emit(DataState.Success(filteredList))
    }
}