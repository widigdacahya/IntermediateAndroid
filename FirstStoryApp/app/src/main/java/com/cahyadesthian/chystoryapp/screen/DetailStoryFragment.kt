package com.cahyadesthian.chystoryapp.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

}