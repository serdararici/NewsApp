package com.serdararici.newsapp.ui.fragment

import android.os.Bundle
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
import com.serdararici.newsapp.databinding.FragmentAdminYeniDuyuruBinding
import com.serdararici.newsapp.ui.viewmodel.AdminYeniDuyuruViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class AdminYeniDuyuruFragment : Fragment() {
    private var _binding : FragmentAdminYeniDuyuruBinding?=null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewmodelAdminYeniDuyuru : AdminYeniDuyuruViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : AdminYeniDuyuruViewModel by viewModels()
        viewmodelAdminYeniDuyuru = tempViewModel

        val bottomNavigationView =
            requireActivity()!!.findViewById<BottomNavigationView>(R.id.BottomNavAdmin)
        bottomNavigationView.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminYeniDuyuruBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.btnNewAnnouncementSaveAdmin.setOnClickListener {
            var newAnnouncementTitle = binding.etNewAnnouncementTitleAdmin.text.toString()
            var newAnnouncementContent = binding.etNewAnnouncementContentAdmin.text.toString()
            var newAnnouncementDate = getDate()

            if (checkAll()) {
                binding.progressBarNewAnnouncementAdmin.visibility = View.VISIBLE
                addNewAnnouncement(newAnnouncementTitle,newAnnouncementContent,newAnnouncementDate)
                navController.navigate(R.id.action_adminYeniDuyuruFragment_to_adminDuyuruFragment)
                Toast.makeText(requireContext(), getString(R.string.newAnnouncementSavedMessage), Toast.LENGTH_LONG).show()
            }
        }

    }


    fun addNewAnnouncement(newAnnouncementTitle:String, newAnnouncementContent:String,newAnnouncementDate:String) {
        viewmodelAdminYeniDuyuru.addNewAnnouncement(newAnnouncementTitle,newAnnouncementContent,newAnnouncementDate)
    }


    private fun checkAll():Boolean {
        val announcementTitle = binding.etNewAnnouncementTitleAdmin.text.toString()
        val announcementContent = binding.etNewAnnouncementContentAdmin.text.toString()
        clearErrors()
        if(announcementTitle == ""){
            binding.textInputNewAnnouncementTitleAdmin.error = getString(R.string.newAnnouncementTitleTitleRequired)
            Toast.makeText(requireContext(), R.string.newNewsTitleTitleRequired, Toast.LENGTH_LONG).show()
            return false
        }
        if(announcementContent == ""){
            binding.textInputNewAnnouncementContentAdmin.error = getString(R.string.newAnnouncementContentRequired)
            Toast.makeText(requireContext(), R.string.newAnnouncementContentRequired, Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    private fun clearErrors() {
        binding.textInputNewAnnouncementTitleAdmin.error = null
        binding.textInputNewAnnouncementContentAdmin.error = null
    }

    fun getDate(): String {
        // Şu anki tarihi al
        val currentDate = Date()

        // Uygulamanın mevcut dil ayarına göre tarih formatını ayarla
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")

        // Tarihi formatla ve döndür
        return dateFormat.format(currentDate)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.BottomNavAdmin)
        bottomNavigationView.visibility = View.VISIBLE
        _binding = null
    }

}