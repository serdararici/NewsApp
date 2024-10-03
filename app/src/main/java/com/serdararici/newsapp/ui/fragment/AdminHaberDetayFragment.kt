package com.serdararici.newsapp.ui.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.serdararici.newsapp.R
import com.serdararici.newsapp.databinding.FragmentAdminHaberDetayBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class AdminHaberDetayFragment : Fragment() {
    var _binding: FragmentAdminHaberDetayBinding?=null
    val binding get() = _binding!!
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminHaberDetayBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val bundle: AdminHaberDetayFragmentArgs by navArgs()
        val newsDetails = bundle.newsDetails

        binding.tvAdminHaberDetayTitle.setText(newsDetails.konu)
        binding.tvAdminHaberContent.setText(newsDetails.icerik)
        binding.tvAdminHaberDetayUsername.setText(newsDetails.kullaniciAdi)
        binding.tvAdminHaberDetayDate.setText(newsDetails.gecerlilikTarihi)
        val imagePath = newsDetails.resim
        val file = java.io.File(imagePath)

        if (file.exists()) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            binding.ivAdminHaberDetay.setImageBitmap(bitmap)  // Resmi bir ImageView'de göster
        } else {
            binding.ivAdminHaberDetay.setImageResource(R.drawable.image_not_found)
        }

    }

}