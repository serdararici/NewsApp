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
import com.serdararici.newsapp.databinding.FragmentHaberDetayBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HaberDetayFragment : Fragment() {
    private var _binding : FragmentHaberDetayBinding?=null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val bundle: HaberDetayFragmentArgs by navArgs()
        val newsDetails = bundle.newsDetails

        binding.tvHaberDetayTitle.setText(newsDetails.konu)
        binding.tvHaberContent.setText(newsDetails.icerik)
        binding.tvHaberDetayUsername.setText(newsDetails.kullaniciAdi)
        binding.tvHaberDetayDate.setText(newsDetails.gecerlilikTarihi)
        val imagePath = newsDetails.resim
        val file = java.io.File(imagePath)

        if (file.exists()) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            binding.ivHaberDetay.setImageBitmap(bitmap)  // Resmi bir ImageView'de göster
        } else {
            binding.ivHaberDetay.setImageResource(R.drawable.image_not_found)
        }
    }

}