package com.serdararici.newsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.serdararici.newsapp.R
import com.serdararici.newsapp.data.entity.Haber
import com.serdararici.newsapp.databinding.FragmentAdminHaberBinding
import com.serdararici.newsapp.ui.adapter.AdminHaberlerAdapter
import com.serdararici.newsapp.ui.adapter.HaberlerAdapter

class AdminHaberFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding : FragmentAdminHaberBinding?= null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        binding.btnAddNewNewsAdmin.setOnClickListener{
            navController.navigate(R.id.action_adminHaberFragment_to_adminYeniHaberFragment)
        }

        val haberlerListesi = ArrayList<Haber>()
        val h1 = Haber(1, "Haber1", "İcerik1dfgsdfgsdgsdfgsdfdgfgsdfbsdfbdsfsbdsfbdsbdsfdfbsdfbdsbd", "10.07.2024", "link1","serdararici")
        val h2 = Haber(2, "Haber2", "İcerik2", "10.07.2024", "link1","serdararici")
        val h3 = Haber(3, "Haber3", "İcerik3", "10.07.2024", "link1","serdararici")
        val h4 = Haber(4, "Haber4", "İcerik4", "10.07.2024", "link1","serdararici")
        haberlerListesi.add(h1)
        haberlerListesi.add(h2)
        haberlerListesi.add(h3)
        haberlerListesi.add(h4)

        val adapter = AdminHaberlerAdapter(requireContext(),haberlerListesi)
        binding.rvNewsAdmin.adapter = adapter

    }

    override fun onQueryTextSubmit(query: String): Boolean {
        search(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        search(newText)
        return true
    }

    fun search (searchingWord: String){
        Log.e("search", searchingWord)
    }


    override fun onResume() {
        super.onResume()

    }


}