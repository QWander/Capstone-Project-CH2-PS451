package com.dicoding.qwander.data.retrofit

import com.dicoding.qwander.data.response.RecomendationsResponse
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

//    @Multipart
    @FormUrlEncoded
    @POST("recommendation")
    suspend fun getRecommendations(
        @Field("Generation") generation : String,
        @Field("Gender") gender: String,
        @Field("City") city: String,
        @Field("Category") category: String,
        @Field("Price_Category") price: String

//        @Part("Generation") generation : RequestBody,
//        @Part("Gender") gender: RequestBody,
//        @Part("City") city: RequestBody,
//        @Part("Category") category: RequestBody,
//        @Part("Price_Category") price: RequestBody
    ) : RecomendationsResponse

}