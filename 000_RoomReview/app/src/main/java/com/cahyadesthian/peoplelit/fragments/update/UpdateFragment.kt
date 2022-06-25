package com.cahyadesthian.peoplelit.fragments.update

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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cahyadesthian.peoplelit.R
import com.cahyadesthian.peoplelit.model.People
import com.cahyadesthian.peoplelit.viewmodel.PeopleViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var edtFirstName: EditText
    private lateinit var edtLastName: EditText
    private lateinit var edtAge: EditText
    private lateinit var btnUpdate: Button

    private lateinit var mPeopleViewModel: PeopleViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPeopleViewModel = ViewModelProvider(this).get(PeopleViewModel::class.java)

        edtFirstName = view.findViewById(R.id.edtfirstname_update)
        edtFirstName.setText(args.currentPeople.firstName)

        edtLastName = view.findViewById(R.id.edtlastname_update)
        edtLastName.setText(args.currentPeople.lastName)

        edtAge = view.findViewById(R.id.edtage_update)
        edtAge.setText(args.currentPeople.age.toString())

        btnUpdate = view.findViewById(R.id.btn_submit_update)
        btnUpdate.setOnClickListener {
            val firstNameUpdate = edtFirstName.text.toString()
            val lastNameUpdate = edtLastName.text.toString()
            val ageUpdate = Integer.parseInt(edtAge.text.toString())

            if(inputCheck(firstNameUpdate,lastNameUpdate,edtAge.text)) {
                val updatedPeople = People(args.currentPeople.id, firstNameUpdate,lastNameUpdate,ageUpdate)
                mPeopleViewModel.updatePeople(updatedPeople)
                Toast.makeText(requireContext(),"Updated", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            } else {
                Toast.makeText(requireContext(),"Please fill all item", Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)
    }


}