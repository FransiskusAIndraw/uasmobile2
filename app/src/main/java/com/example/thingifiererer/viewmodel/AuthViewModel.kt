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

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.getUser(username, password)
            _isLoggedIn.value = user != null
            _user.value = user
        }
    }

    fun register(username: String, password: String, fullName: String, email: String, phoneNumber: String) {
        viewModelScope.launch {
            val user = User(username = username, password = password, fullName = fullName, email = email, phoneNumber = phoneNumber)
            userRepository.insertUser(user)
            _isLoggedIn.value = true
            _user.value = user
        }
    }

    fun logout() {
        _isLoggedIn.value = false
        _user.value = null
    }
}
