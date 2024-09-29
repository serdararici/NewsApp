package com.serdararici.newsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.serdararici.newsapp.R
import com.serdararici.newsapp.databinding.FragmentHaberDetayBinding


class HaberDetayFragment : Fragment() {
    private var _binding : FragmentHaberDetayBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHaberDetayBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}