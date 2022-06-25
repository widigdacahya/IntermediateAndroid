package com.cahyadesthian.peoplelit.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.peoplelit.R
import com.cahyadesthian.peoplelit.data.People
import com.cahyadesthian.peoplelit.data.PeopleViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AddFragment : Fragment() {

    private lateinit var mPeopleViewModel: PeopleViewModel

    private lateinit var firstNameEdt: EditText
    private lateinit var lastNameEdt: EditText
    private lateinit var ageEdt: EditText
    private lateinit var btnSubmit: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPeopleViewModel = ViewModelProvider(this).get(PeopleViewModel::class.java)


        btnSubmit = view.findViewById(R.id.btn_submit)

        firstNameEdt = view.findViewById(R.id.edtfirstname)
        lastNameEdt = view.findViewById(R.id.edtlastname)
        ageEdt = view.findViewById(R.id.edtage)


        btnSubmit.setOnClickListener {

            val firstName = firstNameEdt.text.toString()
            val lastName = lastNameEdt.text.toString()
            val age = ageEdt.text

            if(inputCheck(firstName,lastName,age)) {
                 val people = People(0, firstName,lastName,Integer.parseInt(age.toString()))

                mPeopleViewModel.addPeople(people)
                Toast.makeText(requireContext(), "People Added", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            } else {
                Toast.makeText(requireContext(),"Please fill data", Toast.LENGTH_SHORT).show()
            }


        }

    }

    private fun inputCheck(firstName: String, lastName: String, age:Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }


}