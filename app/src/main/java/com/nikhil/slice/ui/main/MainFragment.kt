package com.nikhil.slice.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nikhil.slice.R
import com.nikhil.slice.ui.main.adapter.TweetRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val adapter = TweetRecyclerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tweets_RecyclerView.adapter = adapter
    }

}