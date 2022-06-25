package com.cahyadesthian.peoplelit.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.peoplelit.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() {

//    val floatingActionBUtton : FloatingActionButton? = view?.findViewById(R.id.fab_add)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

//        val floatingActionButton = view.findViewById<FloatingActionButton>(R.id.fab_add)
//
//        floatingActionButton?.setOnClickListener {
//            findNavController().navigate(R.id.action_listFragment_to_addFragment)
//        }




        return view

    }


}