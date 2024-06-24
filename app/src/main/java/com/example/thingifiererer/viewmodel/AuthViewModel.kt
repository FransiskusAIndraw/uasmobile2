package com.example.thingifiererer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thingifiererer.model.User
import com.example.thingifiererer.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.getUser(username, password)
            _isLoggedIn.value = user != null
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch {
            val user = User(username = username, password = password)
            userRepository.insertUser(user)
            _isLoggedIn.value = true
        }
    }
}
