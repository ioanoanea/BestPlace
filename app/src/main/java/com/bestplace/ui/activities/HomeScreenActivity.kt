package com.bestplace.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bestplace.R
import com.bestplace.data.model.Location
import com.bestplace.data.repository.FirebaseRepository
import com.bestplace.data.repository.LocationRepository
import com.bestplace.ui.fragments.CategoriesFragment
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HomeScreenActivity : AppCompatActivity() {

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        supportFragmentManager.beginTransaction().add(R.id.container, CategoriesFragment()).commit()

        val executorService: ExecutorService = Executors.newFixedThreadPool(4)

        val locationRepository = LocationRepository(executorService)

        locationRepository.getById("2") { result ->
            when(result) {
                is FirebaseRepository.Result.Success<Location> ->
                    if (result.data != null){
                        Toast.makeText(this, result.data.latitude.toString(), Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show()
                    }
                else ->
                    Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

}