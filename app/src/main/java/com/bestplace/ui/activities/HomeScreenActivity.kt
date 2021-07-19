package com.bestplace.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.bestplace.R
import com.bestplace.data.model.Place
import com.bestplace.data.repository.LocationRepository
import com.bestplace.data.repository.Repository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class HomeScreenActivity : AppCompatActivity() {
    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val locationRepository = LocationRepository()

        locationRepository.setOnUpdateListener {
            Toast.makeText(this, "${locationRepository.items[0].latitude}", Toast.LENGTH_SHORT).show()
        }

        locationRepository.getAll()

    }
}