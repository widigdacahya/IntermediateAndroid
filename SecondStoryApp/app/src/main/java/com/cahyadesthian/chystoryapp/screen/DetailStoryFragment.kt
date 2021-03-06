package com.cahyadesthian.chystoryapp.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.FragmentDetailStoryBinding
import com.cahyadesthian.chystoryapp.model.ItemListStory
import com.cahyadesthian.chystoryapp.screen.util.glideLoad


class DetailStoryFragment : Fragment() {

    private var _detailStoryFragBinding : FragmentDetailStoryBinding? = null
    private val detailStoryBinding get() = _detailStoryFragBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _detailStoryFragBinding = FragmentDetailStoryBinding.inflate(inflater,container,false)
        return detailStoryBinding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storyData = DetailStoryFragmentArgs.fromBundle(arguments as Bundle).story
        setStory(storyData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _detailStoryFragBinding = null
    }



    private fun setStory(storyItem : ItemListStory) {

        detailStoryBinding?.apply {
            ivStoryDetail.glideLoad(storyItem.photoUrl)
            tvStoryUsername.text = storyItem.name
            tvStoryDescription.text = storyItem.description
        }

    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.apply {
            show()
            title = "Detail Story"
        }

    }

    override fun onPrepareOptionsMenu(menu: Menu) {

        val menuHiding = menu.findItem(R.id.mapstory_menu)

        if(menuHiding!=null) {
            menuHiding.setVisible(false)
            menuHiding.isEnabled = false
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


}