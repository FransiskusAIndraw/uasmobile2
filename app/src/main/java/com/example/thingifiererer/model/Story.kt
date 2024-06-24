package com.example.thingifiererer.model

data class Story(
    val id: Long,
    val title: String,
    val url: String?,
    val by: String,
    val time: Long,
    val score: Int,
    val descendants: Int
)
