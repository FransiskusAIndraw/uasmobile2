package com.example.thingifiererer.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: HackerNewsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://hacker-news.firebaseio.com/v0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HackerNewsApi::class.java)
    }
}
