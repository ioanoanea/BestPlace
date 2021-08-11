package com.bestplace.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.bestplace.R
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
                    placeListViewModel.getByCategory(newText)
                }
                return true
            }
        })

    }

    private fun setViews() {
        this.searchBox = findViewById(R.id.search_box)
    }
}
