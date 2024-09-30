package com.serdararici.newsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.serdararici.newsapp.R
import com.serdararici.newsapp.data.entity.Haber
import com.serdararici.newsapp.databinding.FragmentHaberBinding
import com.serdararici.newsapp.ui.adapter.HaberlerAdapter

class HaberFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding : FragmentHaberBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHaberBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchViewMain.setOnQueryTextListener(this@HaberFragment)

        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())

        val haberlerListesi = ArrayList<Haber>()
        val h1 = Haber(1, "Haber1", "İcerik1dfgsdfgsdgsdfgsdfdgfgsdfbsdfbdsfsbdsfbdsbdsfdfbsdfbdsbd", "10.07.2024", "link1","serdararici")
        val h2 = Haber(1, "Haber1", "İcerik1", "10.07.2024", "link1","serdararici")
        val h3 = Haber(1, "Haber1", "İcerik1", "10.07.2024", "link1","serdararici")
        val h4 = Haber(1, "Haber1", "İcerik1", "10.07.2024", "link1","serdararici")
        haberlerListesi.add(h1)
        haberlerListesi.add(h2)
        haberlerListesi.add(h3)
        haberlerListesi.add(h4)

        val adapter = HaberlerAdapter(requireContext(),haberlerListesi)
        binding.rvNews.adapter = adapter

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

}