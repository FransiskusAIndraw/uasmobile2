package com.example.thingifiererer.repository

import com.example.thingifiererer.dao.UserDao
import com.example.thingifiererer.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getUser(username: String, password: String): User? {
        return userDao.getUser(username, password)
    }
}
