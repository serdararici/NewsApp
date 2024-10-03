package com.serdararici.newsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.serdararici.newsapp.R
import com.serdararici.newsapp.databinding.FragmentAdminDuyuruBinding
import com.serdararici.newsapp.ui.adapter.AdminDuyurularAdapter
import com.serdararici.newsapp.ui.adapter.AdminHaberlerAdapter
import com.serdararici.newsapp.ui.viewmodel.AdminDuyuruViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminDuyuruFragment : Fragment() {
    private var _binding: FragmentAdminDuyuruBinding?=null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewmodelAdminDuyuru : AdminDuyuruViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : AdminDuyuruViewModel by viewModels()
        viewmodelAdminDuyuru = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminDuyuruBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.rvAnnouncementAdmin.layoutManager = LinearLayoutManager(requireContext())

        binding.btnAddNewAnnouncementAdmin.setOnClickListener{
            navController.navigate(R.id.action_adminDuyuruFragment_to_adminYeniDuyuruFragment)
        }

        viewmodelAdminDuyuru.duyurularListesiLive.observe(viewLifecycleOwner) {
            val adapter = AdminDuyurularAdapter(requireContext(),it,viewmodelAdminDuyuru)
            binding.rvAnnouncementAdmin.adapter = adapter
        }
    }


    override fun onResume() {
        super.onResume()
        viewmodelAdminDuyuru.getAllAnnouncement()
    }

}