package com.nikhil.slice.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.nikhil.slice.MainCoroutineRule
import com.nikhil.slice.getOrAwaitValueTest
import com.nikhil.slice.repositories.FakeTweetsRepo
import com.nikhil.slice.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setup() {
        mainViewModel = MainViewModel(FakeTweetsRepo(), SavedStateHandle())
    }

    @Test
    fun `get all tweets returns all tweets`() {
        mainViewModel.getAllTweets()
        val dataState = mainViewModel.listOfTweetsState.getOrAwaitValueTest()
        if (dataState is DataState.Success) {
            assertThat(dataState.data).hasSize(3)
        }
    }

    @Test
    fun `search for matched text return filtered list`() {
        mainViewModel.searchTweet("Nikhil")
        val dataState = mainViewModel.listOfTweetsState.getOrAwaitValueTest()
        if (dataState is DataState.Success) {
            assertThat(dataState.data).hasSize(1)
        }
    }

    @Test
    fun `search for matched with extra spaces return filtered list`() {
        mainViewModel.searchTweet("  Nikhil   ")
        val dataState = mainViewModel.listOfTweetsState.getOrAwaitValueTest()
        if (dataState is DataState.Success) {
            assertThat(dataState.data).hasSize(1)
        }
    }

    @Test
    fun `search for unmatched text return empty list`() {
        mainViewModel.searchTweet("NNNikhil")
        val dataState = mainViewModel.listOfTweetsState.getOrAwaitValueTest()
        if (dataState is DataState.Success) {
            assertThat(dataState.data).isEmpty()
        }
    }

    @Test
    fun `search for empty returns all tweets`() {
        mainViewModel.searchTweet("")
        val dataState = mainViewModel.listOfTweetsState.getOrAwaitValueTest()
        if (dataState is DataState.Success) {
            assertThat(dataState.data).hasSize(3)
        }
    }
}