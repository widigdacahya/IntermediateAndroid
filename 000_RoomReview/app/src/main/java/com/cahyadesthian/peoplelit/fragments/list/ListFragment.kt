package com.cahyadesthian.peoplelit.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cahyadesthian.peoplelit.R
import com.cahyadesthian.peoplelit.data.PeopleViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() {

//    val floatingActionBUtton : FloatingActionButton? = view?.findViewById(R.id.fab_add)

    private lateinit var mPeopleViewModel : PeopleViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val peopleListAdapter = ListAdapter()
        val recyclerView : RecyclerView = view.findViewById(R.id.rc_list_people)
        recyclerView.adapter = peopleListAdapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //people viewmodel
        mPeopleViewModel = ViewModelProvider(this).get(PeopleViewModel::class.java)
        mPeopleViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            peopleListAdapter.setData(it)
        })


        val fabToAdd : FloatingActionButton = view.findViewById(R.id.fab_add)
        fabToAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)


        return view

    }


}