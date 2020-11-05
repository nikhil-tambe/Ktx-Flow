package com.nikhil.slice.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.slice.R
import com.nikhil.slice.model.Tweet
import com.nikhil.slice.databinding.LayoutItemTweetBinding

class TweetRecyclerAdapter :
    ListAdapter<Tweet, TweetRecyclerAdapter.TweetViewHolder>(TweetDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        return TweetViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TweetViewHolder private constructor(private val binding: LayoutItemTweetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): TweetViewHolder {
                val inflate = DataBindingUtil.inflate<LayoutItemTweetBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_item_tweet,
                    parent,
                    false
                )
                return TweetViewHolder(inflate)
            }
        }

        fun bind(item: Tweet) {
            binding.tweet = item
            binding.executePendingBindings()
        }
    }

}

class TweetDiffUtil : DiffUtil.ItemCallback<Tweet>() {

    override fun areItemsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
        return oldItem.timestamp == newItem.timestamp
    }

    override fun areContentsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
        return oldItem == newItem
    }

}
