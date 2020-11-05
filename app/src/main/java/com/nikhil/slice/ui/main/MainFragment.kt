package com.nikhil.slice.ui.main

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nikhil.slice.R
import com.nikhil.slice.ui.main.adapter.TweetRecyclerAdapter
import com.nikhil.slice.util.DataState
import com.nikhil.slice.util.isOnline
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private val adapter = TweetRecyclerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tweets_RecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        tweets_RecyclerView.adapter = adapter

        observeDataState()

        search_Button.setOnClickListener {
            val s = name_TextInputEditText.text.toString()
            viewModel.searchTweet(s)
        }
        name_TextInputEditText.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    val service: InputMethodManager? =
                        requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    service?.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
    }

    private fun observeDataState() {
        viewModel.getAllTweets()
        viewModel.listOfTweetsState.observe(viewLifecycleOwner) {
            loading_Lottie.visibility = View.GONE
            when (it) {
                is DataState.Loading -> {
                    loading_Lottie.visibility = View.VISIBLE
                    if (!loading_Lottie.isAnimating) {
                        loading_Lottie.animate()
                    }
                }
                is DataState.Success -> {
                    adapter.submitList(it.data)
                }
                is DataState.Error -> {
                    Timber.e(it.e)
                    var message = it.e.localizedMessage
                    if (it.e is UnknownHostException && !requireContext().isOnline()) {
                        message = "Please enable internet and restart app"
                    }
                    message?.let { msg ->
                        Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}