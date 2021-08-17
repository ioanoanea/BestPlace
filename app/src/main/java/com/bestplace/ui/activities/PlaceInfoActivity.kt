package com.bestplace.ui.activities

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.bestplace.R
import com.bestplace.data.model.Location
import com.bestplace.data.model.Place
import com.bestplace.data.viewModel.LocationViewModel
import com.bestplace.data.viewModel.PlaceViewModel
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.lang.Exception

class PlaceInfoActivity : AppCompatActivity() {

    // viewModels
    private val placeViewModel: PlaceViewModel by viewModels()
    private val locationViewModel: LocationViewModel by viewModels()
    // views
    private lateinit var backBtn: ImageButton
    private lateinit var title: TextView
    private lateinit var image: ImageView
    private lateinit var description: TextView
    private lateinit var address: TextView
    private lateinit var distance: TextView
    private lateinit var openInMaps: TextView
    // location client
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_info)

        // set layout views
        setViews()

        // fused location client provider setup
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // set back button on click
        this.backBtn.setOnClickListener {
            onBackPressed()
        }

        // place observer
        val placeObserver = Observer<Place> { place ->
            // load the image
            Glide.with(this)
                .load(place.picture)
                .centerCrop()
                .into(image)
            this.title.text = place.name
            this.description.text = place.description
            this.address.text = place.address

            // get current location observer
            val locationObserver = Observer<Location> { location ->
                this.distance.text = "${place.getDistance(location)}"
            }
            locationViewModel.getCurrentLocation().observe(this, locationObserver)
        }
        placeViewModel.getPlace().observe(this, placeObserver)

        // fetch data
        intent.getStringExtra("ID")?.let { placeViewModel.fetchPlace(it) }
        try {
            fetchLocation()
        } catch (exception: Exception) {
            Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Set layout views
     */
    private fun setViews() {
        this.backBtn = findViewById(R.id.back_button)
        this.title = findViewById(R.id.title_text)
        this.image = findViewById(R.id.image)
        this.description = findViewById(R.id.description)
        this.address = findViewById(R.id.address)
        this.distance = findViewById(R.id.distance)
        this.openInMaps = findViewById(R.id.open_in_maps_btn)
    }

    /**
     * Check location permission
     */
    private fun checkLocationPermission() {
        // check if location permission is granted
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            // request location permission
            ActivityCompat.requestPermissions(this, arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ), 101)
        }
    }

    /**
     * Get current location
     */
    private fun fetchLocation() {
        checkLocationPermission()
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : android.location.Location? ->
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    locationViewModel.setLocation(com.bestplace.data.model.Location(null, location.latitude, location.longitude))
                }
            }

    }
}