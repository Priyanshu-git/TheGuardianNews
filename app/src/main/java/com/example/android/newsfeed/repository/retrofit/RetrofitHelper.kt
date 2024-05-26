package com.example.android.newsfeed.repository.retrofit

import com.example.android.newsfeed.models.GuardianResponse
import com.example.android.newsfeed.repository.retrofit.ApiInterface
import com.example.android.newsfeed.repository.retrofit.ApiService
import org.json.JSONObject


class RetrofitHelper(private val apiService: ApiService) : ApiInterface {
    override suspend fun fetchNews(url: String): GuardianResponse {
        return apiService.getNewsData(url)
    }
}
