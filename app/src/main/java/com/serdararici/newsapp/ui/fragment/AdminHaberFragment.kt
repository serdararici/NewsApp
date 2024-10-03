package com.serdararici.newsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.serdararici.newsapp.R
import com.serdararici.newsapp.databinding.FragmentAdminHaberBinding
import com.serdararici.newsapp.ui.adapter.AdminHaberlerAdapter
import com.serdararici.newsapp.ui.adapter.HaberlerAdapter
import com.serdararici.newsapp.ui.viewmodel.AdminHaberViewModel
import com.serdararici.newsapp.ui.viewmodel.AdminYeniHaberViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class AdminHaberFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding : FragmentAdminHaberBinding?= null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewmodelAdminHaber : AdminHaberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : AdminHaberViewModel by viewModels()
        viewmodelAdminHaber = tempViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminHaberBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.searchViewAdmin.setOnQueryTextListener(this@AdminHaberFragment)

        binding.rvNewsAdmin.layoutManager = LinearLayoutManager(requireContext())

        var todayDate = getDate()
        binding.tvDateAdminHaber.setText(todayDate)

        binding.btnAddNewNewsAdmin.setOnClickListener{
            navController.navigate(R.id.action_adminHaberFragment_to_adminYeniHaberFragment)
        }

        viewmodelAdminHaber.haberlerListesiLive.observe(viewLifecycleOwner) {
            val adapter = AdminHaberlerAdapter(requireContext(),it,viewmodelAdminHaber)
            binding.rvNewsAdmin.adapter = adapter
        }

    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewmodelAdminHaber.searchNews(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewmodelAdminHaber.searchNews(newText)
        return true
    }

    fun getDate(): String {
        // Şu anki tarihi al
        val currentDate = Date()

        // Uygulamanın mevcut dil ayarına göre tarih formatını ayarla
        val dateFormat = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())

        // Tarihi formatla ve döndür
        return dateFormat.format(currentDate)
    }


    override fun onResume() {
        super.onResume()
        viewmodelAdminHaber.getAllNews()
    }


}