package com.nikhil.slice.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.nikhil.slice.R
import com.nikhil.slice.ui.main.adapter.TweetRecyclerAdapter
import com.nikhil.slice.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private val adapter = TweetRecyclerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tweets_RecyclerView.adapter = adapter

        allTweets()
    }

    private fun allTweets() {
        viewModel.getAllTweets()
        viewModel.listOfTweetsState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    // show loading
                }
                is DataState.Success -> {
                    adapter.submitList(it.data)
                }
                is DataState.Error -> {
                    it.e.localizedMessage?.let {msg ->
                        Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

}