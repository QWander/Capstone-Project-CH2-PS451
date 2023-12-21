package com.dicoding.qwander.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.qwander.R
import com.dicoding.qwander.databinding.ActivityDetailBinding
import com.google.android.gms.maps.GoogleMap

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        supportActionBar?.apply {
            setLogo(R.drawable.logo_text_tranparant)
            setDisplayUseLogoEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = null
        }

        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            val myFragment = MapsFragment()

            fragmentTransaction.add(R.id.fragmentContainer, myFragment)
            fragmentTransaction.commit()
        }

    }


}