package com.bestplace.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bestplace.R
import com.bestplace.data.model.Place
import com.bestplace.data.repository.FirebaseRepository
import com.bestplace.data.viewModel.OnUpdateListener
import com.bestplace.data.viewModel.PlaceListViewModel
import com.bestplace.ui.fragments.CategoriesFragment


class HomeScreenActivity : AppCompatActivity() {

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        supportFragmentManager.beginTransaction().add(R.id.container, CategoriesFragment()).commit()

        val placeListViewModel = PlaceListViewModel()

        placeListViewModel.setOnUpdateListener(object: OnUpdateListener {
            override fun onUpdate() {
                for (place in placeListViewModel.places) {
                    Toast.makeText(
                        this@HomeScreenActivity,
                        "name: " + place.name + "\n" +
                        "location id: " + place.locationId + "\n" +
                        "description: " + place.description + "\n" +
                        "category: " + place.category,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onNullResult() {
                Toast.makeText(this@HomeScreenActivity,
                    "Nothing was found", Toast.LENGTH_SHORT).show()
            }

            override fun onError(e: FirebaseRepository.Result<MutableList<Place>>) {
                Toast.makeText(this@HomeScreenActivity,
                    e.toString(), Toast.LENGTH_SHORT).show()
            }

        })

        placeListViewModel.searchByName("te")
    }

}