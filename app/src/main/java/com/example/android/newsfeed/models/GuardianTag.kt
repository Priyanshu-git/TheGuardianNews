package com.example.android.newsfeed.models

data class GuardianTag(
    val id: String,
    val type: String,
    val webTitle: String,
    val webUrl: String,
    val apiUrl: String,
    val references: List<Any>,
    val bio: String,
    val bylineImageUrl: String,
    val bylineLargeImageUrl: String,
    val firstName: String,
    val lastName: String,
    val twitterHandle: String? = null
)