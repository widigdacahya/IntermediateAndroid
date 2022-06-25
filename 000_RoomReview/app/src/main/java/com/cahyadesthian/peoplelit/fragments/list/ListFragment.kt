package com.cahyadesthian.peoplelit.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cahyadesthian.peoplelit.R
import com.cahyadesthian.peoplelit.viewmodel.PeopleViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() {

//    val floatingActionBUtton : FloatingActionButton? = view?.findViewById(R.id.fab_add)

    private lateinit var mPeopleViewModel : PeopleViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_delete) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _,_ ->
                mPeopleViewModel.deleteAllPeoples()
                Toast.makeText(requireContext(),"Everone Deleted", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("No") {_,_ ->}
            builder.setTitle("Delete all People?")
            builder.setMessage("All deleted people can come back")
            builder.create().show()
        }

        return super.onOptionsItemSelected(item)
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