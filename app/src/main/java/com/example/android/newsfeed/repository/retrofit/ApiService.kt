package com.example.android.newsfeed.repository.retrofit

import com.example.android.newsfeed.models.GuardianResponse
import com.example.android.newsfeed.utils.Constants
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getNewsData(@Url url: String): GuardianResponse
}
