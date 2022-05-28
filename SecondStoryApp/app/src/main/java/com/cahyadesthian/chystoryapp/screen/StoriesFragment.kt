package com.cahyadesthian.chystoryapp.screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.database.StoryItemDatabase
import com.cahyadesthian.chystoryapp.databinding.FragmentStoriesBinding
import com.cahyadesthian.chystoryapp.databinding.ItemStoryBinding
import com.cahyadesthian.chystoryapp.model.ItemListStory
import com.cahyadesthian.chystoryapp.networking.Api
import com.cahyadesthian.chystoryapp.screen.StoriesFragmentArgs.Companion.fromBundle
import com.cahyadesthian.chystoryapp.screen.util.AdapterListStory
import com.cahyadesthian.chystoryapp.screen.util.AdapterLoadingState
import com.cahyadesthian.chystoryapp.screen.util.StoryRepository
import com.cahyadesthian.chystoryapp.viewmodel.StoriesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class StoriesFragment : Fragment() {

    private var _storiesBinding : FragmentStoriesBinding? = null
    private val storiesBinding get() = _storiesBinding!!

    private var userToken = "String"

    private val storyViewModel by viewModels<StoriesViewModel> {
        StoriesViewModel.Factory(StoryRepository(
            StoryItemDatabase.getDatabase(context as Context), Api.retorofitApiService(),getString(R.string.bearerAuth,userToken)
        ))
    }

    private var newStory = false


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

        //userToken = fromBundle(arguments as Bundle).token

        val args = StoriesFragmentArgs.fromBundle(arguments as Bundle)
        userToken = args.token
        newStory = args.newStory

//        storyViewModel.isLoading.observe(viewLifecycleOwner) {
//            loadingTHings(it)
//        }
//
//        storyViewModel.error.observe(viewLifecycleOwner) {
//            it.getIfNotHandled()?.let { msg ->
//                Toast.makeText(activity,"oops", Toast.LENGTH_SHORT).show()
//                storyViewModel.getAllStory()
//            }
//        }
//
//        storyViewModel.stories.observe(viewLifecycleOwner) {
//            adapterStorySetup(ArrayList(it))
//        }




        val adapterListStory = AdapterListStory().apply {
            setOnItemClickCallback(object : AdapterListStory.OnItemClickCallback {
                override fun onItemClicked(story: ItemListStory, storyBinding: ItemStoryBinding) {
                    view.findNavController().navigate(R.id.action_storiesFragment_to_detailStoryFragment, bundleOf("story" to story),null)
                }
            })
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }


        val rvLayoutManager = LinearLayoutManager(context)

        storiesBinding.rvStories.layoutManager = rvLayoutManager
        storiesBinding.rvStories.setHasFixedSize(true)

        storiesBinding.rvStories.adapter = adapterListStory.withLoadStateFooter(
            AdapterLoadingState {
                adapterListStory.retry()
            }
        )


        storyViewModel.listStory.observe(viewLifecycleOwner) {

            storiesBinding.apply {
                adapterListStory.submitData(viewLifecycleOwner.lifecycle, it)

                viewLifecycleOwner.lifecycleScope.launch {
                    delay(800)
                    if(newStory) {
                        newStory = false
                        storiesBinding.rvStories.scrollToPosition(0)
                    }
                }

            }


        }


        storiesBinding.fabAddStory.setOnClickListener {
            val toNewStory = StoriesFragmentDirections.actionStoriesFragmentToAddStoryFragment(userToken)
            findNavController().navigate(toNewStory)
        }

//        //after uplaod
//        setFragmentResultListener(AddStoryFragment.ADD_RES) { _, bundle ->
//            if(bundle.getBoolean(AddStoryFragment.IS_SUCCESS)){
//                newStoryHere()
//            }
//        }

    }

    private fun loadingTHings(isLoading: Boolean) {
        if(isLoading) storiesBinding.pbStories.visibility = View.VISIBLE else storiesBinding.pbStories.visibility = View.GONE
    }

//    private fun adapterStorySetup(storyList: ArrayList<ItemListStory>) {
//        storiesBinding.rvStories.adapter = AdapterListStory(storyList).apply {
//            setOnItemClickCallback(object : AdapterListStory.OnItemClickCallback {
//
//                override fun onItemClicked(story: ItemListStory, storyBinding: ItemStoryBinding) {
//
//                    view?.findNavController()?.navigate(
//                        R.id.action_storiesFragment_to_detailStoryFragment,
//                        bundleOf("story" to story),null
//
//                    )
//                }
//
//            })
//        }
//    }

//    private fun newStoryHere() {
//        Toast.makeText(activity, "Your story uploaded \uD83C\uDF89 ", Toast.LENGTH_SHORT).show()
//        storyViewModel.getAllStory()
//    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _storiesBinding = null
    }
}