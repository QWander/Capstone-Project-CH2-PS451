package com.dicoding.qwander.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class RecommendationsResponse(

	@field:SerializedName("top3_recommendations")
	val top3Recommendations: List<RecommendationsItem>
)

@Parcelize
data class RecommendationsItem(

	@field:SerializedName("Description")
	val description: String? = null,

	@field:SerializedName("Place_Name")
	val placeName: String? = null,

	@field:SerializedName("Price")
	val price: Int,

	@field:SerializedName("Rating")
	val rating: Double? = null,

	@field:SerializedName("Long")
	val long: Double,

	@field:SerializedName("Lat")
	val lat: Double
) : Parcelable
