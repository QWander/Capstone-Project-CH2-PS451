package com.dicoding.qwander.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.qwander.data.response.RecomendationsResponse
import com.dicoding.qwander.data.response.ResultState
import com.dicoding.qwander.data.retrofit.ApiConfig
import com.dicoding.qwander.data.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException

class RecommendationRepositoryprivate constructor(
    val apiService: ApiService,
) {

//    fun getRecommendation() = LiveData{
////        emit()
//        emit(ResultState.Loading)
////
////        try {
////            val apiService = ApiConfig.getApiService()
////            val successResponse = apiService.getReccomendations()
////        }
//    }

    fun getRecommendations(generation: String, gender: String, city: String, category: String, priceCategory: String) = liveData {
//        emit(ResultState.Loading)
        try {
            val apiService = ApiConfig.getApiService()
            val successResponse = apiService.getRecommendations(generation, gender, city, category, priceCategory)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, RecomendationsResponse::class.java)
//            emit(ResultState.Error(errorResponse.message.toString()))
        }

    }

}