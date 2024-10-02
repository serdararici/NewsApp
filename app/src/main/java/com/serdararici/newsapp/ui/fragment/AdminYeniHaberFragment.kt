package com.serdararici.newsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.serdararici.newsapp.R
import com.serdararici.newsapp.databinding.FragmentAdminYeniHaberBinding
import com.serdararici.newsapp.ui.viewmodel.AdminYeniHaberViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminYeniHaberFragment : Fragment() {
    private var _binding: FragmentAdminYeniHaberBinding?=null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewmodelAdminYeniHaber : AdminYeniHaberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : AdminYeniHaberViewModel by viewModels()
        viewmodelAdminYeniHaber = tempViewModel

        val bottomNavigationView =
            requireActivity()!!.findViewById<BottomNavigationView>(R.id.BottomNavAdmin)
        bottomNavigationView.visibility = View.GONE

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminYeniHaberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


        binding.btnNewNewsSaveAdmin.setOnClickListener {
            var newNewsTitle = binding.etNewNewsTitleAdmin.text.toString()
            var newNewsContent = binding.etNewNewsContentAdmin.text.toString()

            if (checkAll()) {
                binding.progressBarNewNewsAdmin.visibility = View.VISIBLE
                addNewNews(newNewsTitle,newNewsContent)
            }
        }

    }

    fun addNewNews(newNewsTitle:String, newNewsContent:String) {
        viewmodelAdminYeniHaber.addNewNews(newNewsTitle,newNewsContent)
    }


    private fun checkAll():Boolean {
        val newsTitle = binding.etNewNewsTitleAdmin.text.toString()
        val newsContent = binding.etNewNewsContentAdmin.text.toString()
        clearErrors()
        if(newsTitle == ""){
            binding.textInputNewNewsTitleAdmin.error = getString(R.string.newNewsTitleTitleRequired)
            Toast.makeText(requireContext(), R.string.newNewsTitleTitleRequired, Toast.LENGTH_LONG).show()
            return false
        }
        if(newsContent == ""){
            binding.textInputNewNewsContentAdmin.error = getString(R.string.newNewsContentRequired)
            Toast.makeText(requireContext(), R.string.newNewsContentRequired, Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    private fun clearErrors() {
        binding.textInputNewNewsTitleAdmin.error = null
        binding.textInputNewNewsContentAdmin.error = null
    }


    override fun onDestroyView() {
        super.onDestroyView()
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.BottomNavAdmin)
        bottomNavigationView.visibility = View.VISIBLE
        _binding = null
    }

}