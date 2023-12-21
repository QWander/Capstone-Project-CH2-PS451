package com.dicoding.qwander.view.recommendations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.qwander.R
import com.dicoding.qwander.data.response.RecomendationsItem

class RecomendationsAvtivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recomendations)

        supportActionBar?.apply {
            setLogo(R.drawable.logo_text_tranparant) // Replace with your logo resource
            setDisplayUseLogoEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = null
        }

        val tourismPlace = listOf(
            RecomendationsItem(15,"Monumen Nasional atau yang populer disingkat dengan Monas atau Tugu Monas adalah monumen peringatan setinggi 132 meter (433 kaki) yang didirikan untuk mengenang perlawanan dan perjuangan rakyat Indonesia untuk merebut kemerdekaan dari pemerintahan kolonial Hindia Belanda. Pembangunan monumen ini dimulai pada tanggal 17 Agustus 1961 di bawah perintah presiden Soekarno dan dibuka untuk umum pada tanggal 12 Juli 1975. Tugu ini dimahkotai lidah api yang dilapisi lembaran emas yang melambangkan semangat perjuangan yang menyala-nyala. Monumen Nasional terletak tepat di tengah Lapangan Medan Merdeka, Jakarta Pusat.",
                "Budaya", 1, "Monumen Nasional", 20000.00, 4.6, 106.8271528, "Jakarta", -6.1753924),

            RecomendationsItem(15,"Monumen Nasional atau yang populer disingkat dengan Monas atau Tugu Monas adalah monumen peringatan setinggi 132 meter (433 kaki) yang didirikan untuk mengenang perlawanan dan perjuangan rakyat Indonesia untuk merebut kemerdekaan dari pemerintahan kolonial Hindia Belanda. Pembangunan monumen ini dimulai pada tanggal 17 Agustus 1961 di bawah perintah presiden Soekarno dan dibuka untuk umum pada tanggal 12 Juli 1975. Tugu ini dimahkotai lidah api yang dilapisi lembaran emas yang melambangkan semangat perjuangan yang menyala-nyala. Monumen Nasional terletak tepat di tengah Lapangan Medan Merdeka, Jakarta Pusat.",
                "Budaya", 1, "Monumen Nasional", 20000.00, 4.6, 106.8271528, "Jakarta", -6.1753924),

            RecomendationsItem(15,"Monumen Nasional atau yang populer disingkat dengan Monas atau Tugu Monas adalah monumen peringatan setinggi 132 meter (433 kaki) yang didirikan untuk mengenang perlawanan dan perjuangan rakyat Indonesia untuk merebut kemerdekaan dari pemerintahan kolonial Hindia Belanda. Pembangunan monumen ini dimulai pada tanggal 17 Agustus 1961 di bawah perintah presiden Soekarno dan dibuka untuk umum pada tanggal 12 Juli 1975. Tugu ini dimahkotai lidah api yang dilapisi lembaran emas yang melambangkan semangat perjuangan yang menyala-nyala. Monumen Nasional terletak tepat di tengah Lapangan Medan Merdeka, Jakarta Pusat.",
                "Budaya", 1, "Monumen Nasional", 20000.00, 4.6, 106.8271528, "Jakarta", -6.1753924),

            )

    }
}