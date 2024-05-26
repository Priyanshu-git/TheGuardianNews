package com.example.android.newsfeed.repository.retrofit

import com.example.android.newsfeed.models.GuardianResponse

interface ApiInterface {
    suspend fun fetchNews(url: String):GuardianResponse


}
