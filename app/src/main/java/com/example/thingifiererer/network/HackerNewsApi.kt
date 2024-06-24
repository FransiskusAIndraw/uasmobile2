package com.example.thingifiererer.network

import com.example.thingifiererer.model.Story
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsApi {
    @GET("topstories.json?print=pretty")
    suspend fun getTopStories(): List<Long>

    @GET("item/{id}.json?print=pretty")
    suspend fun getStory(@Path("id") id: Long): Story
}