package com.dicoding.qwander.data.retrofit

import com.dicoding.qwander.data.repository.UserRequestBody
import com.dicoding.qwander.data.response.RecommendationsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("predict")
    fun getRecommendations(
    @Body body: UserRequestBody
    ) : Call<RecommendationsResponse>



}