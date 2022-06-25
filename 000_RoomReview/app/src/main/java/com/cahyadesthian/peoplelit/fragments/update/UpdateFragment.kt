package com.cahyadesthian.peoplelit.fragments.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.navArgs
import com.cahyadesthian.peoplelit.R


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var edtFirstName: EditText
    private lateinit var edtLastName: EditText
    private lateinit var edtAge: EditText
    private lateinit var btnUpdate: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtFirstName = view.findViewById(R.id.edtfirstname_update)
        edtFirstName.setText(args.currentPeople.firstName)

        edtLastName = view.findViewById(R.id.edtlastname_update)
        edtLastName.setText(args.currentPeople.lastName)

        edtAge = view.findViewById(R.id.edtage_update)
        edtAge.setText(args.currentPeople.age.toString())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)
    }


}