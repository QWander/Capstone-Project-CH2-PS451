package com.dicoding.qwander.view.recommendations

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.qwander.data.repository.RecommendationRepository
import com.dicoding.qwander.data.repository.UserRequestBody
import com.dicoding.qwander.data.response.RecommendationsItem
import com.dicoding.qwander.data.response.RecommendationsResponse
import com.dicoding.qwander.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendationViewModel(
    private val repository: RecommendationRepository


) : ViewModel() {

    private val _listRecommendation = MutableLiveData<List<RecommendationsItem>>()
    val listRecommendation: LiveData<List<RecommendationsItem>> = _listRecommendation




//    fun getRecommendation(generation: String,
//                                  gender: String,
//                                  city: String,
//                                  category: String,
//                                  priceCategory: String) {
//        val client = ApiConfig.getApiService().getRecommendations(generation,
//        gender, city,
//        category, priceCategory)
//
//        client.enqueue(object : Callback<RecommendationsResponse> {
//            override fun onResponse(
//                call: Call<RecommendationsResponse>,
//                response: Response<RecommendationsResponse>
//            ) {
//                if (response.isSuccessful) {
//                    _listRecommendation.value = response.body()?.top3Recommendations
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//            override fun onFailure(call: Call<RecommendationsResponse>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//        })
//    }
    fun getRecommendation(userRequestBody: UserRequestBody) = repository.getRecommendations(userRequestBody)


}