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
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.serdararici.newsapp.R
import com.serdararici.newsapp.databinding.FragmentAdminHaberGuncelleBinding
import com.serdararici.newsapp.ui.viewmodel.AdminHaberGuncelleViewModel
import com.serdararici.newsapp.ui.viewmodel.AdminYeniHaberViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminHaberGuncelleFragment : Fragment() {
    private var _binding: FragmentAdminHaberGuncelleBinding?=null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewmodelAdminHaberGuncelle : AdminHaberGuncelleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : AdminHaberGuncelleViewModel by viewModels()
        viewmodelAdminHaberGuncelle = tempViewModel

        val bottomNavigationView =
            requireActivity()!!.findViewById<BottomNavigationView>(R.id.BottomNavAdmin)
        bottomNavigationView.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminHaberGuncelleBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val bundle: AdminHaberGuncelleFragmentArgs by navArgs()
        val news = bundle.news

        binding.etUpdateNewsTitleAdmin.setText(news.konu)
        binding.etUpdateNewsContentAdmin.setText(news.icerik)

        binding.btnUpdateNewsSaveAdmin.setOnClickListener {
            var updateNewsTitle = binding.etUpdateNewsTitleAdmin.text.toString()
            var updateNewsContent = binding.etUpdateNewsContentAdmin.text.toString()

            if (checkAll()) {
                binding.progressBarNewNewsAdmin.visibility = View.VISIBLE
                updateNews(news.id,updateNewsTitle,updateNewsContent,news.gecerlilikTarihi)
                navController.navigate(R.id.action_adminHaberGuncelleFragment_to_adminHaberFragment)
                Toast.makeText(requireContext(), getString(R.string.updateNewsMessage), Toast.LENGTH_LONG).show()
            }
        }
    }


    fun updateNews(newsId: Int, updateNewsTitle:String, updateNewsContent:String,updateNewsDate:String) {
        viewmodelAdminHaberGuncelle.updateNews(newsId,updateNewsTitle,updateNewsContent,updateNewsDate)
    }


    private fun checkAll():Boolean {
        val updateNewsTitle = binding.etUpdateNewsTitleAdmin.text.toString()
        val updateNewsContent = binding.etUpdateNewsContentAdmin.text.toString()
        clearErrors()
        if(updateNewsTitle == ""){
            binding.textInputUpdateNewsTitleAdmin.error = getString(R.string.newNewsTitleTitleRequired)
            Toast.makeText(requireContext(), R.string.newNewsTitleTitleRequired, Toast.LENGTH_LONG).show()
            return false
        }
        if(updateNewsContent == ""){
            binding.textInputUpdateNewsContentAdmin.error = getString(R.string.newNewsContentRequired)
            Toast.makeText(requireContext(), R.string.newNewsContentRequired, Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    private fun clearErrors() {
        binding.textInputUpdateNewsTitleAdmin.error = null
        binding.textInputUpdateNewsContentAdmin.error = null
    }


    override fun onDestroyView() {
        super.onDestroyView()
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.BottomNavAdmin)
        bottomNavigationView.visibility = View.VISIBLE
        _binding = null
    }

}