package com.dicoding.qwander.data.repository

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRequestBody(

	@field:SerializedName("Generation")
	val generation: String? = null,

	@field:SerializedName("Category")
	val category: String? = null,

	@field:SerializedName("Price_Category")
	val priceCategory: String? = null,

	@field:SerializedName("Gender")
	val gender: String? = null,

	@field:SerializedName("City")
	val city: String? = null
) : Parcelable
