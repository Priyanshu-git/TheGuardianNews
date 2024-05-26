package com.example.android.newsfeed.repository

import com.example.android.newsfeed.repository.retrofit.RetrofitBuilder
import com.example.android.newsfeed.repository.retrofit.RetrofitHelper
import com.example.android.newsfeed.utils.Constants
import kotlinx.coroutines.flow.flow

class NewsRepository {
    private val apiHelper = RetrofitHelper(RetrofitBuilder.apiService)

    suspend fun fetchNews(url: String) = flow {
        emit(apiHelper.fetchNews(url))
    }
}