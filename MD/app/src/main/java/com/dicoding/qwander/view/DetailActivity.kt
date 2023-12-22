package com.dicoding.qwander.view

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.StringRes
import com.dicoding.qwander.R
import com.dicoding.qwander.data.response.RecommendationsItem
import com.dicoding.qwander.databinding.ActivityDetailBinding
import com.google.android.gms.maps.GoogleMap
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.apply {
            setLogo(R.drawable.logo_text_tranparant)
            setDisplayUseLogoEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = null
        }


        val recommendation = intent.getParcelableExtra<RecommendationsItem>("Recommendation")
        Log.i(TAG, "datanya detail $recommendation")

        if (recommendation != null) {

            binding.tvAttractionName1.text = recommendation.placeName.toString()

            binding.tvAttractionRating1.text = recommendation.rating.toString()

            binding.tvAttractionPrice1.text = formatAsRupiah(recommendation.price)

            binding.tvAttractionDescription1.text = recommendation.description.toString()


            if (savedInstanceState == null) {
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()

                val myFragment = MapsFragment()
                myFragment.arguments = Bundle().apply {
                    putDouble(MapsFragment.EXTRA_LAT, recommendation.lat)
                    putDouble(MapsFragment.EXTRA_LONG, recommendation.long)
                    putString(MapsFragment.EXTRA_NAME, recommendation.placeName)
                }


                fragmentTransaction.add(R.id.fragmentContainer, myFragment)
                fragmentTransaction.commit()
            }


        }





    }

    private fun formatAsRupiah(amount: Int): String {
        val indonesiaLocale = Locale("id", "ID") // Create a Locale for Indonesia
        val format = NumberFormat.getCurrencyInstance(indonesiaLocale)

        val rupiahCurrency = Currency.getInstance("IDR") // Get the currency instance for IDR
        format.currency = rupiahCurrency

        return format.format(amount)
    }


}