package com.cahyadesthian.chystoryapp.screen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.ActivityMainBinding
import com.cahyadesthian.chystoryapp.screen.util.SessionDataPreference
import com.cahyadesthian.chystoryapp.viewmodel.SharedViewModel


internal val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding

    private val sharedViewModel by viewModels<SharedViewModel> {
        SharedViewModel.Factory(SessionDataPreference.getDataStoreInstance(dataStore))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)
        //supportActionBar?.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.logout_menu, menu)
        return true
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val navContoller = findNavController(R.id.fragmentContainerView)

        when(item.itemId) {
            R.id.logout_menu -> {
                logout(navContoller)
            }
        }

        return true

    }

    private fun logout(navController: NavController) {

        sharedViewModel.storeToken("")

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val the_graph = inflater.inflate(R.navigation.the_nav)
        the_graph.setStartDestination(R.id.authFragment)
        navController.graph = the_graph

    }


}