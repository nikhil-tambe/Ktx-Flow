package com.nikhil.slice.repositories

import com.nikhil.slice.model.Tweet
import com.nikhil.slice.util.DataState
import kotlinx.coroutines.flow.Flow

interface ITweetsRepo {

    suspend fun getTweets() : Flow<DataState<List<Tweet>>>

    suspend fun getFilteredList(query: String, animate: Boolean): Flow<DataState<List<Tweet>>>

}