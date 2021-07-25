package com.bestplace.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bestplace.R
import com.bestplace.ui.fragments.CategoriesFragment

class HomeScreenActivity : AppCompatActivity() {

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        supportFragmentManager.beginTransaction().add(R.id.container, CategoriesFragment()).commit()
    }

}