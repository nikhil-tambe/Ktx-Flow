package com.nikhil.slice.di

import com.nikhil.slice.db.tweets.TweetsDao
import com.nikhil.slice.model.TweetMapper
import com.nikhil.slice.network.TwitterApi
import com.nikhil.slice.repositories.ITweetsRepo
import com.nikhil.slice.repositories.TweetsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideTweetsRepository (
        twitterApi: TwitterApi,
        tweetsDao: TweetsDao,
        tweetMapper: TweetMapper
    ) = TweetsRepository(
            twitterApi = twitterApi,
            tweetsDao = tweetsDao,
            mapper = tweetMapper
        ) as ITweetsRepo

}