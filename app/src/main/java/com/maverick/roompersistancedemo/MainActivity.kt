package com.maverick.roompersistancedemo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.elevation.SurfaceColors
import com.maverick.roompersistancedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBarWithNavController(findNavController(R.id.navHostFragment))
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FF018786")))
        window.apply {
            statusBarColor =
                resources.getColor(R.color.teal_700)
            navigationBarColor =
                resources.getColor(R.color.teal_700)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navHostFragment).navigateUp() || super.onSupportNavigateUp()
    }

}