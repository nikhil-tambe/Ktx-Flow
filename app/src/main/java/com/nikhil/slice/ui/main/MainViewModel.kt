package com.nikhil.slice.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.nikhil.slice.model.Tweet
import com.nikhil.slice.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _listOfTweetsState: MutableLiveData<DataState<List<Tweet>>> = MutableLiveData()
    val listOfTweetsState: LiveData<DataState<List<Tweet>>>
        get() = _listOfTweetsState

    fun getAllTweets() {
        viewModelScope.launch {
            mainRepository.getTweets()
                .onEach { dataState ->
                    _listOfTweetsState.value = dataState
                }
                .launchIn(viewModelScope)
        }
    }

    fun searchTweet(s: String) {
        viewModelScope.launch {
            mainRepository.getFilteredList(s)
                .onEach { dataState ->
                    _listOfTweetsState.value = dataState
                }
                .launchIn(viewModelScope)
        }
    }

}
