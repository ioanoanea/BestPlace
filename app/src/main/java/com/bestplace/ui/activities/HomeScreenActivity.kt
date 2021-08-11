package com.bestplace.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import com.bestplace.R
import com.bestplace.data.viewModel.LocationViewModel
import com.bestplace.data.viewModel.PlaceListViewModel
import com.bestplace.ui.fragments.CategoriesFragment
import com.bestplace.ui.fragments.PlacesFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class HomeScreenActivity : AppCompatActivity() {

    private val placeListViewModel: PlaceListViewModel by viewModels()
    private val locationViewModel: LocationViewModel by viewModels()
    private lateinit var searchBox: androidx.appcompat.widget.SearchView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        setViews()

        // set fragment as Categories Fragment
        supportFragmentManager.beginTransaction().replace(R.id.container, CategoriesFragment()).commit()

        /*
        // get location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    val currentLocation = com.bestplace.data.model.Location(location.latitude, location.longitude)
                    locationViewModel.setLocation(currentLocation)
                }
            }
         */

        locationViewModel.setLocation(com.bestplace.data.model.Location(1.34435435, 5.43544354))

        // set search box on query text listener
        searchBox.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    try {
                        placeListViewModel.search(newText)
                    } catch (exception: Exception) {
                        Toast.makeText(this@HomeScreenActivity, exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                    // set fragment as Places Fragment if needed
                    if (supportFragmentManager.fragments.last() !is PlacesFragment) {
                        supportFragmentManager.beginTransaction().replace(R.id.container, PlacesFragment()).addToBackStack(null).commit()
                    }
                }
                return true
            }
        })

        searchBox.setOnCloseListener{
            // set fragment as Categories Fragment
            supportFragmentManager.beginTransaction().replace(R.id.container, CategoriesFragment()).commit()
            false
        }

    }

    private fun setViews() {
        this.searchBox = findViewById(R.id.search_box)
    }
}
