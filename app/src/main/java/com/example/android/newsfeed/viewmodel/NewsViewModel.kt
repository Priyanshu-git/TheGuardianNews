package com.example.android.newsfeed.viewmodel

import androidx.lifecycle.ViewModel
import com.example.android.newsfeed.models.GuardianResponse
import com.example.android.newsfeed.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject

class NewsViewModel: ViewModel() {
    val repository = NewsRepository()
    suspend fun fetchNewsData(url: String): Flow<GuardianResponse> {
        return repository.fetchNews(url)
    }
}