package com.dicoding.qwander.di

import android.content.Context
import com.dicoding.qwander.data.repository.RecommendationRepository
//import com.dicoding.qwander.data.repository.RecommendationRepository
import com.dicoding.qwander.data.retrofit.ApiConfig
import kotlinx.coroutines.runBlocking

object Injection {

    fun provideRepository(context: Context): RecommendationRepository {
        val apiService = ApiConfig.getApiService()
        return RecommendationRepository.getInstance(apiService)
    }
}