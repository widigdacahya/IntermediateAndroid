package com.cahyadesthian.ridnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.cahyadesthian.ridnav.databinding.FragmentBlankBinding


class BlankFragment : Fragment() {

    private var _blankFragBinding : FragmentBlankBinding? = null
    private val blanFragBinding get() = _blankFragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_blank, container, false)
        _blankFragBinding = FragmentBlankBinding.inflate(inflater,container,false)
        val view = blanFragBinding.root

        blanFragBinding.tvTextfirstfrag.setOnClickListener {

            val action = BlankFragmentDirections.actionBlankFragmentToSecondFragment(99999)
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _blankFragBinding = null
    }



}