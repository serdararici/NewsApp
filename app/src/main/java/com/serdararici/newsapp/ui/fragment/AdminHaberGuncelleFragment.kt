package com.serdararici.newsapp.ui.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
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
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class AdminHaberGuncelleFragment : Fragment() {
    private var _binding: FragmentAdminHaberGuncelleBinding?=null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewmodelAdminHaberGuncelle : AdminHaberGuncelleViewModel

    private var imageUri: Uri? = null // Seçilen resmin URI'si burada saklanacak

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
        val imagePath = news.resim
        val file = java.io.File(imagePath)

        if (file.exists()) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            binding.ivUpdateNewsImageAdmin.setImageBitmap(bitmap)  // Resmi bir ImageView'de göster
        } else {
            binding.ivUpdateNewsImageAdmin.setImageResource(R.drawable.image_not_found)
        }

        binding.btnUpdateNewsAddImageAdmin.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        binding.btnUpdateNewsSaveAdmin.setOnClickListener {
            var updateNewsTitle = binding.etUpdateNewsTitleAdmin.text.toString()
            var updateNewsContent = binding.etUpdateNewsContentAdmin.text.toString()

            if (checkAll()) {
                binding.progressBarNewNewsAdmin.visibility = View.VISIBLE

                imageUri?.let {
                    val imagePath = saveImageToFileSystem(it)
                    imagePath?.let { path ->
                        // Burada resmi Room veritabanına kaydetmek için DAO işlemi yapılacak
                        updateNews(news.id,updateNewsTitle,updateNewsContent,news.gecerlilikTarihi,path,news.kullaniciAdi)
                        navController.navigate(R.id.action_adminHaberGuncelleFragment_to_adminHaberFragment)
                        Toast.makeText(requireContext(), getString(R.string.updateNewsMessage), Toast.LENGTH_LONG).show()
                    }
                }?: run {
                    // Eğer resim seçilmeden kayıt yapılırsa
                    updateNews(news.id,updateNewsTitle,updateNewsContent,news.gecerlilikTarihi,news.resim,news.kullaniciAdi)
                    navController.navigate(R.id.action_adminHaberGuncelleFragment_to_adminHaberFragment)
                    Toast.makeText(requireContext(), getString(R.string.updateNewsMessage), Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    fun updateNews(newsId: Int, updateNewsTitle:String, updateNewsContent:String,updateNewsDate:String,newNewsImage:String, newNewsUserName:String) {
        viewmodelAdminHaberGuncelle.updateNews(newsId,updateNewsTitle,updateNewsContent,updateNewsDate,newNewsImage,newNewsUserName)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data  // Seçilen resmin URI'si
            imageUri?.let {
                binding.ivUpdateNewsImageAdmin.setImageURI(it) // Önizleme için ImageView'e yükleniyor
            }
        }
    }

    fun saveImageToFileSystem(imageUri: Uri): String? {
        try {
            // Resmi kaydedeceğimiz klasörü tanımlıyoruz
            val directory = File(requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "MyAppImages")
            if (!directory.exists()) {
                directory.mkdirs()
            }

            // Resim dosya adını belirliyoruz (zaman damgası ile)
            val fileName = "IMG_${System.currentTimeMillis()}.jpg"
            val file = File(directory, fileName)

            // Input ve output stream'leri kullanarak resmi kaydediyoruz
            val inputStream = requireActivity().contentResolver.openInputStream(imageUri)
            val outputStream = FileOutputStream(file)
            inputStream?.copyTo(outputStream)
            outputStream.close()
            inputStream?.close()

            // Kaydedilen dosyanın tam yolunu döndürüyoruz
            return file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Image saving failed.", Toast.LENGTH_SHORT).show()
            return null
        }
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