package com.serdararici.newsapp.ui.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.serdararici.newsapp.R
import com.serdararici.newsapp.databinding.FragmentAdminYeniHaberBinding
import com.serdararici.newsapp.ui.viewmodel.AdminYeniHaberViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class AdminYeniHaberFragment : Fragment() {
    private var _binding: FragmentAdminYeniHaberBinding?=null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewmodelAdminYeniHaber : AdminYeniHaberViewModel

    private var imageUri: Uri? = null // Seçilen resmin URI'si burada saklanacak

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

        binding.btnNewNewsAddImageAdmin.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        binding.btnNewNewsSaveAdmin.setOnClickListener {
            var newNewsTitle = binding.etNewNewsTitleAdmin.text.toString()
            var newNewsContent = binding.etNewNewsContentAdmin.text.toString()
            var newNewsDate = getDate()

            val sharedPref = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
            val userName = sharedPref.getString("userName", null)

            if (checkAll()) {
                binding.progressBarNewNewsAdmin.visibility = View.VISIBLE

                imageUri?.let {
                    val imagePath = saveImageToFileSystem(it)
                    imagePath?.let { path ->
                        // Burada resmi Room veritabanına kaydetmek için DAO işlemi yapılacak
                        addNewNews(newNewsTitle,newNewsContent,newNewsDate,path,userName!!)
                        Log.e("imagePath", path)
                        navController.navigate(R.id.action_adminYeniHaberFragment_to_adminHaberFragment)
                        Toast.makeText(requireContext(), getString(R.string.newNewsSavedMessage), Toast.LENGTH_LONG).show()
                    }
                }?: run {
                    // Eğer resim seçilmeden kayıt yapılırsa
                    addNewNews(newNewsTitle,newNewsContent,newNewsDate,"",userName!!)
                    navController.navigate(R.id.action_adminYeniHaberFragment_to_adminHaberFragment)
                    Toast.makeText(requireContext(), getString(R.string.newNewsSavedMessage), Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    fun addNewNews(newNewsTitle:String, newNewsContent:String,newNewsDate:String,newNewsImage:String, newNewsUserName:String) {
        viewmodelAdminYeniHaber.addNewNews(newNewsTitle,newNewsContent,newNewsDate,newNewsImage, newNewsUserName)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data  // Seçilen resmin URI'si
            imageUri?.let {
                binding.ivNewNewsImageAdmin.setImageURI(it) // Önizleme için ImageView'e yükleniyor
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