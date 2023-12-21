package com.dicoding.qwander.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class RecomendationsResponse(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("recomendations")
	val recomendations: List<RecomendationsItem?>? = null,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? = null
)
@Parcelize
data class RecomendationsItem(

	@field:SerializedName("Time_Minutes")
	val timeMinutes: Int? = null,

	@field:SerializedName("Description")
	val description: String? = null,

	@field:SerializedName("Category")
	val category: String? = null,

	@field:SerializedName("Place_Id")
	val placeId: Int? = null,

	@field:SerializedName("Place_Name")
	val placeName: String? = null,

	@field:SerializedName("Price")
	val price: Double? = null,


	@field:SerializedName("Rating")
	val rating: Double? = null,

	@field:SerializedName("Long")
	val long: Double? = null,

	@field:SerializedName("City")
	val city: String? = null,

	@field:SerializedName("Lat")
	val lat: Double? = null
): Parcelable
