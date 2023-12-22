package com.dicoding.qwander.view.recommendations

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.qwander.R
import com.dicoding.qwander.data.repository.UserRequestBody
import com.dicoding.qwander.data.response.RecommendationsItem
import com.dicoding.qwander.data.response.RecommendationsResponse
import com.dicoding.qwander.data.retrofit.ApiConfig
import com.dicoding.qwander.databinding.ActivityRecomendationsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecomendationsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecomendationsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        supportActionBar?.apply {
            setLogo(R.drawable.logo_text_tranparant)
            setDisplayUseLogoEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = null
        }

        val receivedIntent = intent
        val userRequestBody: UserRequestBody? = receivedIntent.getParcelableExtra("userRequestBody")


        if (userRequestBody != null) {
            binding.progressBar.visibility = View.GONE

            getRecommendation(userRequestBody)

           }




        val layoutManager = LinearLayoutManager(this)
        binding.rvRecommendation.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvRecommendation.addItemDecoration(itemDecoration)


    }

    private fun getRecommendation(userRequestBody: UserRequestBody) {
        val client = ApiConfig.getApiService().getRecommendations(userRequestBody)

        client.enqueue(object : Callback<RecommendationsResponse> {
            override fun onResponse(
                call: Call<RecommendationsResponse>,
                response: Response<RecommendationsResponse>
            ) {
                if (response.isSuccessful) {
                  setData(response.body()!!.top3Recommendations)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<RecommendationsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }


    private fun setData(recommendation: List<RecommendationsItem>) {
        val adapter = RecommendationAdapter()
        adapter.submitList(recommendation)
        binding.rvRecommendation.adapter = adapter
        binding.progressBar.visibility = View.GONE
    }
}