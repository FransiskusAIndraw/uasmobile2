package com.example.thingifiererer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thingifiererer.dao.UserDao
import com.example.thingifiererer.model.User

@Database(entities = [User::class], version = 2)  // Tingkatkan versi dari 1 ke 2
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
