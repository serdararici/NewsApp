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
import com.serdararici.newsapp.databinding.FragmentDuyuruBinding
import com.serdararici.newsapp.ui.adapter.AdminDuyurularAdapter
import com.serdararici.newsapp.ui.adapter.DuyurularAdapter
import com.serdararici.newsapp.ui.viewmodel.DuyuruViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DuyuruFragment : Fragment() {
    private var _binding : FragmentDuyuruBinding?=null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewmodelDuyuru : DuyuruViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : DuyuruViewModel by viewModels()
        viewmodelDuyuru = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDuyuruBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.rvAnnouncement.layoutManager = LinearLayoutManager(requireContext())

        viewmodelDuyuru.duyurularListesiLive.observe(viewLifecycleOwner) {
            val adapter = DuyurularAdapter(requireContext(),it,viewmodelDuyuru)
            binding.rvAnnouncement.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewmodelDuyuru.getAllAnnouncement()
    }

}