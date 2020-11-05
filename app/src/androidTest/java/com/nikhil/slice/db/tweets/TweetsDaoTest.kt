package com.nikhil.slice.db.tweets

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.nikhil.slice.db.SliceDB
import com.nikhil.slice.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TweetsDaoTest {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var sliceDB: SliceDB
    private lateinit var tweetsDao: TweetsDao

    @Before
    fun setup() {
        sliceDB = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SliceDB::class.java
        ).allowMainThreadQueries()
            .build()
        tweetsDao = sliceDB.tweetsDao
    }

    @After
    fun teardown() {
        sliceDB.close()
    }

    @Test
    fun testInsert() = runBlockingTest {
        val tweetsEntity = TweetsEntity(
            id = 1,
            name = "Nikhil",
            handle = "@nikhil",
            tweetText = "My first tweet",
            retweetCount = 101,
            favCount = 97,
            image = "some_image_url"
        )
        tweetsDao.insert(tweetsEntity)

        val listOfTweets = tweetsDao.observeAllTweets().getOrAwaitValue()

        assertThat(listOfTweets).contains(tweetsEntity)
    }

    @Test
    fun testDelete() = runBlockingTest {
        val tweetsEntity = TweetsEntity(
            id = 1,
            name = "Nikhil",
            handle = "@nikhil",
            tweetText = "My first tweet",
            retweetCount = 101,
            favCount = 97,
            image = "some_image_url"
        )
        tweetsDao.insert(tweetsEntity)
        tweetsDao.delete(tweetsEntity)

        val listOfTweets = tweetsDao.observeAllTweets().getOrAwaitValue()

        assertThat(listOfTweets).doesNotContain(tweetsEntity)
    }


}