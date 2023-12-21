package com.dicoding.qwander.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.dicoding.qwander.R
import com.dicoding.qwander.databinding.ActivityMainBinding
import com.dicoding.qwander.view.DetailActivity
import com.dicoding.qwander.view.recommendations.RecomendationsAvtivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.apply {
            setLogo(R.drawable.logo_text_tranparant) // Replace with your logo resource
            setDisplayUseLogoEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = null
        }

        val spinnerAge: Spinner = findViewById(R.id.spinnerAge)
        val ageList = (18..40).toList()
        val adapterAge = ArrayAdapter(this, android.R.layout.simple_spinner_item, ageList)
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAge.adapter = adapterAge


        val spinnerGender: Spinner = findViewById(R.id.spinnerGender)
        ArrayAdapter.createFromResource(
            this,
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapterGender ->
            adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerGender.adapter = adapterGender
        }


        val spinnerCity: Spinner = findViewById(R.id.spinnerCity)
        ArrayAdapter.createFromResource(
            this,
            R.array.city_array,
            android.R.layout.simple_spinner_item
        ).also { adapterCity ->
            adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCity.adapter = adapterCity
        }


        val spinnerAttraction: Spinner = findViewById(R.id.spinnerCategory)
        ArrayAdapter.createFromResource(
            this,
            R.array.attraction_category_array,
            android.R.layout.simple_spinner_item
        ).also { adapterAttractionCat ->
            adapterAttractionCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerAttraction.adapter = adapterAttractionCat
        }


        val moveRecomendationButton: Button = findViewById(R.id.submitButton)

        moveRecomendationButton.setOnClickListener {
            // Create an Intent to move to the second activity
//            val intent = Intent(this@MainActivity, RecomendationsAvtivity::class.java)
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            startActivity(intent)
        }

//        setupAction()

    }

//    private fun setupAction() {
//        binding.submitButton.setOnClickListener {
//            val age = binding.spinnerAge
//            val gender = binding.spinnerGender
//            val city = binding.spinnerCity
//            val attractionCategory = binding.spinnerCategory
//            val price = binding.priceInput.text.toString()
//
//            val moveRecomendationButton: Button = findViewById(R.id.submitButton)
//            moveRecomendationButton.setOnClickListener {
//                // Create an Intent to move to the second activity
//                val intent = Intent(this@MainActivity, RecomendationsAvtivity::class.java)
////                val intent = Intent(this@MainActivity, DetailActivity::class.java)
//                startActivity(intent)
//            }
//
//        }
//    }
}