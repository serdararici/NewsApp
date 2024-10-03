package com.serdararici.newsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.serdararici.newsapp.R
import com.serdararici.newsapp.databinding.FragmentDuyuruDetayBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DuyuruDetayFragment : Fragment() {
    private var _binding: FragmentDuyuruDetayBinding?=null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDuyuruDetayBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val bundle: DuyuruDetayFragmentArgs by navArgs()
        val announcementDetails = bundle.announcementDetails

        binding.tvAnnouncementDetayTitle.setText(announcementDetails.konu)
        binding.tvAnnouncementContent.setText(announcementDetails.icerik)
        binding.tvAnnouncementDetayUsername.setText(announcementDetails.kullaniciAdi)
        binding.tvAnnouncementDetayDate.setText(announcementDetails.gecerlilikTarihi)

    }

}