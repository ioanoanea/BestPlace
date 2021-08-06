package com.bestplace.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.bestplace.R
import com.bestplace.data.model.Place
import com.bestplace.data.viewModel.PlaceListViewModel
import com.bestplace.ui.fragments.CategoriesFragment


class HomeScreenActivity : AppCompatActivity() {

    private val placeListViewModel: PlaceListViewModel by viewModels()
    private lateinit var searchBox: androidx.appcompat.widget.SearchView

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        setViews()

        supportFragmentManager.beginTransaction().replace(R.id.container, CategoriesFragment()).commit()

        searchBox.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    placeListViewModel.search(newText)
                }
                return true
            }
        })

        val placesObserver = Observer<MutableList<Place>> {places ->
            Toast.makeText(this, "${places.size}", Toast.LENGTH_SHORT).show()
        }

        placeListViewModel.getPlaces().observe(this, placesObserver)

    }

    private fun setViews() {
        this.searchBox = findViewById(R.id.search_box)
    }
}
