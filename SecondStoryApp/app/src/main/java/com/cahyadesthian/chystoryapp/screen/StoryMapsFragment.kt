package com.cahyadesthian.chystoryapp.screen

import android.content.res.Resources
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.FragmentStoryMapsBinding
import com.cahyadesthian.chystoryapp.model.ItemListStory
import com.cahyadesthian.chystoryapp.viewmodel.StoryMapsViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class StoryMapsFragment : Fragment() {

    private var _storyMapBinding : FragmentStoryMapsBinding ?= null
    private val storyMapsBinding get() = _storyMapBinding

    private var userToken = "String"

    private val mapStoryViewModel by viewModels<StoryMapsViewModel> {
        StoryMapsViewModel.Factory(getString(R.string.bearerAuth,userToken))
    }

    private var focusStory : ItemListStory? = null

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
//        val sydney = LatLng(-34.0, 151.0)
//        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        setStyleMap(googleMap)

        googleMap.setOnInfoWindowClickListener {

            var itemStory = it.tag as ItemListStory

            focusStory = itemStory

            findNavController().navigate(StoriesFragmentDirections.actionStoriesFragmentToDetailStoryFragment(itemStory))

        }

        googleMap.uiSettings.apply {
            isCompassEnabled = true
            isZoomControlsEnabled = true
        }

        mapStoryViewModel.allStory.observe(viewLifecycleOwner) {

            googleMap.clear()

            it.forEach { storyItem ->

                val latLong = LatLng(storyItem.lat, storyItem.lon)

                val marker = googleMap.addMarker(MarkerOptions().position(latLong)
                    .title(storyItem.name)
                    .snippet("See Detail")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)))

                marker?.tag = storyItem
            }

            //zoom to random
            val theStory = focusStory?: it[(1..it.size).random()-1]
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(LatLng(theStory.lat,theStory.lon),8f)
            )


        }


    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_story_maps, container, false)

        _storyMapBinding = FragmentStoryMapsBinding.inflate(inflater,container,false)
        val view = storyMapsBinding?.root

        return view

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        userToken = StoryMapsFragmentArgs.fromBundle(arguments as Bundle).token


        mapStoryViewModel.isLoading.observe(viewLifecycleOwner) {
            loadingThings(it)
        }


        mapStoryViewModel.error.observe(viewLifecycleOwner) {

            it.getIfNotHandled()?.let { msg ->
                Toast.makeText(activity,"oops", Toast.LENGTH_SHORT).show()
                mapStoryViewModel.getAllStoryData()
            }

        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)



    }




    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Map Story"
    }


    fun setStyleMap(mMap : GoogleMap) {

        try {

            context?.let {

                val success = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(it,R.raw.map_style))
                if(!success) {
                    Log.e("StoryMapsFrag", "styleparsefailde", )
                }

            }


        } catch (exception: Resources.NotFoundException) {
            Log.e("StoryMapsFrag", "cant find style: ", exception )
        }

    }

    private fun loadingThings(isLoading: Boolean) {

        if(isLoading) storyMapsBinding?.pbMapstory?.visibility = View.VISIBLE else storyMapsBinding?.pbMapstory?.visibility = View.GONE

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _storyMapBinding = null
    }

}