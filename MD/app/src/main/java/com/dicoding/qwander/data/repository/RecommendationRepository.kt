package com.dicoding.qwander.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.dicoding.qwander.data.response.RecommendationsItem
import com.dicoding.qwander.data.response.RecommendationsResponse
import com.dicoding.qwander.data.retrofit.ApiConfig
import com.dicoding.qwander.data.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException

class RecommendationRepository private constructor(
    val apiService: ApiService
) {

    fun getRecommendations(userRequestBody: UserRequestBody) = liveData<RecommendationsResponse> {
        apiService.getRecommendations(userRequestBody)
    }


//    private val _successResponse = MutableLiveData<List<RecommendationsItem>>()
//    val successResponse: LiveData<List<RecommendationsItem>> = _successResponse

//    fun getRecommendations(generation: String, gender: String, city: String, category: String, priceCategory: String) : LiveData<List<RecommendationsItem>> {
//        Log.i(TAG, "datanya 1rec rep.")
//
//        try {
//            val successResponse = apiService.getRecommendations(generation, gender, city, category, priceCategory)
////            _successResponse.value = successResponse.top3Recommendations
//            return successResponse.top3Recommendations as LiveData<List<RecommendationsItem>>
//
//        } catch (e: HttpException) {
//            val errorBody = e.response()?.errorBody()?.string()
//            val errorResponse = Gson().fromJson(errorBody, RecommendationsResponse::class.java)
//            return errorResponse.top3Recommendations as LiveData<List<RecommendationsItem>>
//
//
//        }
//
//    }
    companion object {
        @Volatile
        private var instance: RecommendationRepository? = null
        fun getInstance(
            apiService: ApiService
        ): RecommendationRepository =
            instance ?: synchronized(this) {
                instance ?: RecommendationRepository(apiService)
            }.also { instance = it }
    }

}