package com.cahyadesthian.chystoryapp.screen

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.FragmentStoriesBinding
import com.cahyadesthian.chystoryapp.databinding.ItemStoryBinding
import com.cahyadesthian.chystoryapp.model.ItemListStory
import com.cahyadesthian.chystoryapp.screen.StoriesFragmentArgs.Companion.fromBundle
import com.cahyadesthian.chystoryapp.screen.util.AdapterListStory
import com.cahyadesthian.chystoryapp.viewmodel.StoriesViewModel


class StoriesFragment : Fragment() {

    private var _storiesBinding : FragmentStoriesBinding? = null
    private val storiesBinding get() = _storiesBinding!!

    private var userToken = "String"

    private val storyViewModel by viewModels<StoriesViewModel> {
        StoriesViewModel.Factory(getString(R.string.bearerAuth, userToken))
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _storiesBinding = FragmentStoriesBinding.inflate(inflater,container,false)
        val view = storiesBinding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userToken = StoriesFragmentArgs.fromBundle(arguments as Bundle).token

        storyViewModel.isLoading.observe(viewLifecycleOwner) {
            loadingTHings(it)
        }

        storyViewModel.error.observe(viewLifecycleOwner) {
            it.getIfNotHandled()?.let { msg ->
                Toast.makeText(activity,"oops", Toast.LENGTH_SHORT).show()
                storyViewModel.getAllStory()
            }
        }

        storyViewModel.stories.observe(viewLifecycleOwner) {
            adapterStorySetup(ArrayList(it))
        }


        val rvLayoutManager = LinearLayoutManager(context)

        storiesBinding.rvStories.layoutManager = rvLayoutManager
        storiesBinding.rvStories.setHasFixedSize(true)


    }

    private fun loadingTHings(isLoading: Boolean) {
        if(isLoading) {
            storiesBinding.pbStories.visibility = View.VISIBLE
        } else {
            storiesBinding.pbStories.visibility = View.GONE
        }

    }

    private fun adapterStorySetup(storyList: ArrayList<ItemListStory>) {
        storiesBinding.rvStories.adapter = AdapterListStory(storyList).apply {
            setOnItemClickCallback(object : AdapterListStory.OnItemClickCallback {
                override fun onItemClicked(story: ItemListStory, storyBinding: ItemStoryBinding) {
                    val extraData = FragmentNavigatorExtras(
                        storyBinding.ivStoryPreview to getString(R.string.image_story, story.id),
                        storyBinding.tvUserUploadBy to getString(R.string.username_story, story.id)
                    )

                    view?.findNavController()?.navigate(
                        R.id.action_storiesFragment_to_detailStoryFragment,
                        bundleOf("story" to story),null,extraData

                    )
                }

            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _storiesBinding = null
    }
}