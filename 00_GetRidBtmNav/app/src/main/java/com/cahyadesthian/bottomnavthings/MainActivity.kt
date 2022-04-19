package com.cahyadesthian.bottomnavthings

import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cahyadesthian.bottomnavthings.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var listener : NavController.OnDestinationChangedListener
    private lateinit var navController : NavController

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val bottomNavigationView = mainBinding.bottomNavMainUI
        navController = findNavController(R.id.fragmentContainerView)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.firstFragment, R.id.secondFragment, R.id.thirdFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

        listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->

            if(destination.id == R.id.firstFragment) {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.brown)))
            } else if(destination.id == R.id.secondFragment) {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.pursian_blue)))
            } else if(destination.id == R.id.thirdFragment) {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.creamy)))
            }


        }

    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }


    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }

}