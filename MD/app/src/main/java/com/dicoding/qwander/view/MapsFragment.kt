package com.dicoding.qwander.view

import android.content.ContentValues.TAG
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.qwander.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {
    private var lat: Double? = -6.1753924
    private var long: Double? = 106.8271528
    private var name: String? = "place name"

    private val callback = OnMapReadyCallback { googleMap ->
        long = arguments?.getDouble(EXTRA_LONG)
        lat = arguments?.getDouble(EXTRA_LAT)
        name = arguments?.getString(EXTRA_NAME)


        val place = LatLng(lat!!, long!!)
        Log.i(TAG, "datanya lat $lat")
        Log.i(TAG, "datanya long $long")
        googleMap.addMarker(MarkerOptions().position(place).title(name))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 15f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    companion object {
        const val EXTRA_LONG = "long"
        const val EXTRA_LAT = "lat"
        const val EXTRA_NAME = "name"
    }
}