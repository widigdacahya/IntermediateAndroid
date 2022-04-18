package com.cahyadesthian.ridnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.cahyadesthian.ridnav.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    val args : SecondFragmentArgs by navArgs()

    private var _secondFragmentBinding :FragmentSecondBinding? = null
    private val seconFragBinding get() = _secondFragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_second, container, false)

        _secondFragmentBinding = FragmentSecondBinding.inflate(inflater,container,false)
        val view = seconFragBinding.root

        val numberPassedFromPreviousFragment = args.numberPassed
        seconFragBinding.tvSecondfrag.text = numberPassedFromPreviousFragment.toString()

        seconFragBinding.tvSecondfrag.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_blankFragment)
        }

        return view

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _secondFragmentBinding = null
    }


}