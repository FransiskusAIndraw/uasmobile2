package com.example.thingifiererer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thingifiererer.model.Story
import com.example.thingifiererer.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoryDetailViewModel : ViewModel() {
    private val _story = MutableStateFlow<Story?>(null)
    val story: StateFlow<Story?> = _story

    fun getStory(id: Long): StateFlow<Story?> {
        viewModelScope.launch {
            val story = RetrofitInstance.api.getStory(id)
            _story.value = story
        }
        return _story
    }
}
