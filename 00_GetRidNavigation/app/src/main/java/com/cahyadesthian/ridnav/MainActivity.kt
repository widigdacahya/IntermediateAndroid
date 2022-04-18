package com.cahyadesthian.ridnav

import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cahyadesthian.ridnav.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var listener : NavController.OnDestinationChangedListener

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        navController  = findNavController(R.id.fragmentContainerView)
        drawerLayout = findViewById(R.id.drawer_layout)
        mainBinding.navigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)

        //we also could use listener to do things
        listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->

            if(destination.id == R.id.blankFragment) {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.orange_500)))
            } else if(destination.id == R.id.secondFragment) {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.sky_400)))
            }

        }

    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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