package com.bestplace.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bestplace.R
import com.bestplace.data.repository.FirebaseRepository
import com.bestplace.data.repository.PlaceRepository
import com.bestplace.ui.fragments.CategoriesFragment
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HomeScreenActivity : AppCompatActivity() {

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        supportFragmentManager.beginTransaction().add(R.id.container, CategoriesFragment()).commit()

        val executorService: ExecutorService = Executors.newFixedThreadPool(4)
        val placeRepository = PlaceRepository(executorService)

        placeRepository.searchByCategory("car") { result ->
            when (result) {
                is FirebaseRepository.Result.Success ->
                    if (result.data != null) {
                        Toast.makeText(this, "${result.data.size}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show()
                    }
                else ->
                    Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

}