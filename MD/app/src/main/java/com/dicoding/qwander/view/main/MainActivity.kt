package com.dicoding.qwander.view.main

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.annotation.RequiresApi
import com.dicoding.qwander.R
import com.dicoding.qwander.data.repository.UserRequestBody
import com.dicoding.qwander.data.response.RecommendationsItem
import com.dicoding.qwander.databinding.ActivityMainBinding
import com.dicoding.qwander.view.recommendations.RecommendationsActivity
import java.time.Year

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        supportActionBar?.apply {
            setLogo(R.drawable.logo_text_tranparant)
            setDisplayUseLogoEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = null
        }

        val spinnerAge = binding.spinnerAge
        val ageList = (18..40).toList()
        val adapterAge = ArrayAdapter(this, android.R.layout.simple_spinner_item, ageList)
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAge.adapter = adapterAge



        val spinnerGender= binding.spinnerGender
        ArrayAdapter.createFromResource(
            this,
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapterGender ->
            adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerGender.adapter = adapterGender
        }


        val spinnerCity = binding.spinnerCity
        ArrayAdapter.createFromResource(
            this,
            R.array.city_array,
            android.R.layout.simple_spinner_item
        ).also { adapterCity ->
            adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCity.adapter = adapterCity
        }


        val spinnerAttraction = binding.spinnerCategory
        ArrayAdapter.createFromResource(
            this,
            R.array.attraction_category_array,
            android.R.layout.simple_spinner_item
        ).also { adapterAttractionCat ->
            adapterAttractionCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerAttraction.adapter = adapterAttractionCat
        }



        val moveRecommendationButton: Button = findViewById(R.id.submitButton)

        moveRecommendationButton.setOnClickListener {
            val generation = getGeneration(spinnerAge.selectedItem.toString())
            val gender = spinnerGender.selectedItem.toString()
            val city = spinnerCity.selectedItem.toString()
            val category = spinnerAttraction.selectedItem.toString()

            val priceStringValue = binding.priceInput.text.toString()
            val priceDoubleValue: Double = try {
                priceStringValue.toDouble()
            } catch (e: NumberFormatException) {
                0.0
            }
            val price = getPriceCategory(priceDoubleValue)

            Log.i(TAG, "datanya generation: $generation")
            Log.i(TAG, "datanya gender: $gender")
            Log.i(TAG, "datanya city: $city")
            Log.i(TAG, "datanya category: $category")
            Log.i(TAG, "datanya price: $price")

            val userRequestBody = UserRequestBody(generation, category, price, gender, city)


            val intent = Intent(this@MainActivity, RecommendationsActivity::class.java)
            intent.putExtra("userRequestBody", userRequestBody)
            startActivity(intent)
        }


    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getGeneration(age: String): String {
        val currentYear = Year.now().value
        val birthYear = currentYear - age.toInt()

        return when {
            birthYear >= 2013 -> "Gen-Alpha"
            birthYear in 1997..2012 -> "Gen-Z"
            birthYear in 1981..1996 -> "Milenial"
            birthYear in 1965..1980 -> "Gen-X"
            birthYear in 1946..1964 -> "Baby Boomers"
            birthYear < 1946 -> "Traditionalists/Silent Generation"
            else -> "Invalid age input"
        }
    }
    private fun getPriceCategory(price: Double): String {

        return if (price < 50000) {
            "Murah"
        } else if (price > 125000) {
            "Mahal"
        } else {
            "Sedang"
        }
    }

}