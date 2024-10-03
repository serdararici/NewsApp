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
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.serdararici.newsapp.R
import com.serdararici.newsapp.databinding.FragmentAdminDuyuruGuncelleBinding
import com.serdararici.newsapp.ui.viewmodel.AdminDuyuruGuncelleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminDuyuruGuncelleFragment : Fragment() {
    private var _binding: FragmentAdminDuyuruGuncelleBinding?=null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewmodelAdminDuyuruGuncelle : AdminDuyuruGuncelleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : AdminDuyuruGuncelleViewModel by viewModels()
        viewmodelAdminDuyuruGuncelle = tempViewModel

        val bottomNavigationView =
            requireActivity()!!.findViewById<BottomNavigationView>(R.id.BottomNavAdmin)
        bottomNavigationView.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminDuyuruGuncelleBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val bundle: AdminDuyuruGuncelleFragmentArgs by navArgs()
        val announcement = bundle.announcement

        binding.etUpdateAnnouncementTitleAdmin.setText(announcement.konu)
        binding.etUpdateAnnouncementContentAdmin.setText(announcement.icerik)

        binding.btnUpdateAnnouncementSaveAdmin.setOnClickListener {
            var updateAnnouncementTitle = binding.etUpdateAnnouncementTitleAdmin.text.toString()
            var updateAnnouncementContent = binding.etUpdateAnnouncementContentAdmin.text.toString()

            if (checkAll()) {
                binding.progressBarUpdateAnnouncementAdmin.visibility = View.VISIBLE
                updateAnnouncement(announcement.id,updateAnnouncementTitle,updateAnnouncementContent,announcement.gecerlilikTarihi)
                navController.navigate(R.id.action_adminDuyuruGuncelleFragment_to_adminDuyuruFragment)
                Toast.makeText(requireContext(), getString(R.string.updateAnnouncementMessage), Toast.LENGTH_LONG).show()
            }
        }
    }


    fun updateAnnouncement(announcementId: Int, updateAnnouncementTitle:String, updateAnnouncementContent:String,updateAnnouncementDate:String) {
        viewmodelAdminDuyuruGuncelle.updateAnnouncement(announcementId,updateAnnouncementTitle,updateAnnouncementContent,updateAnnouncementDate)
    }


    private fun checkAll():Boolean {
        val updateAnnouncementTitle = binding.etUpdateAnnouncementTitleAdmin.text.toString()
        val updateAnnouncementContent = binding.etUpdateAnnouncementContentAdmin.text.toString()
        clearErrors()
        if(updateAnnouncementTitle == ""){
            binding.textInputUpdateAnnouncementTitleAdmin.error = getString(R.string.newAnnouncementTitleTitleRequired)
            Toast.makeText(requireContext(), R.string.newAnnouncementTitleTitleRequired, Toast.LENGTH_LONG).show()
            return false
        }
        if(updateAnnouncementContent == ""){
            binding.textInputUpdateAnnouncementContentAdmin.error = getString(R.string.newAnnouncementContentRequired)
            Toast.makeText(requireContext(), R.string.newAnnouncementContentRequired, Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    private fun clearErrors() {
        binding.textInputUpdateAnnouncementTitleAdmin.error = null
        binding.textInputUpdateAnnouncementContentAdmin.error = null
    }


    override fun onDestroyView() {
        super.onDestroyView()
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.BottomNavAdmin)
        bottomNavigationView.visibility = View.VISIBLE
        _binding = null
    }
}