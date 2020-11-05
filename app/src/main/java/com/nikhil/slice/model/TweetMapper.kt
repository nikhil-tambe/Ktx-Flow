package com.nikhil.slice.model


import com.nikhil.slice.db.tweets.TweetsEntity
import com.nikhil.slice.network.response.ResponseTweetData
import com.nikhil.slice.util.Mapper
import javax.inject.Inject

class TweetMapper
@Inject constructor() : Mapper<ResponseTweetData, TweetsEntity, Tweet> {

    override fun responseToEntity(data: ResponseTweetData): TweetsEntity {
        return TweetsEntity(
            name = data.name,
            handle = data.handle,
            image = data.image,
            favCount = data.favCount,
            retweetCount = data.retweetCount,
            tweetText = data.tweetText
        )
    }

    override fun entityToModel(entity: TweetsEntity): Tweet {
        return Tweet(
            name = entity.name,
            handle = entity.handle,
            image = entity.image,
            favCount = entity.favCount,
            retweetCount = entity.retweetCount,
            tweetText = entity.tweetText,
            id = entity.id
        )
    }

    fun entityListToModelList(entityList: List<TweetsEntity>): List<Tweet> {
        return entityList.map { entityToModel(it) }
    }

}