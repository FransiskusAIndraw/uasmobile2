package com.example.thingifiererer.di

import androidx.room.Room
import com.example.thingifiererer.database.AppDatabase
import com.example.thingifiererer.repository.UserRepository
import com.example.thingifiererer.viewmodel.AuthViewModel
import com.example.thingifiererer.viewmodel.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single { get<AppDatabase>().userDao() }

    single { UserRepository(get()) }

    viewModel { AuthViewModel(get()) }
    viewModel { ProductViewModel() }
}
