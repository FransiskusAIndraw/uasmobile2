package com.example.thingifiererer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thingifiererer.model.Story
import com.example.thingifiererer.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoriesViewModel : ViewModel() {
    private val _stories = MutableStateFlow<List<Story>>(emptyList())
    val stories: StateFlow<List<Story>> = _stories

    init {
        fetchTopStories()
    }

    private fun fetchTopStories() {
        viewModelScope.launch {
            val storyIds = RetrofitInstance.api.getTopStories()
            val storyList = storyIds.take(20).map { id -> RetrofitInstance.api.getStory(id) }
            _stories.value = storyList
        }
    }
}
