package com.serdararici.newsapp.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.serdararici.newsapp.R
import com.serdararici.newsapp.databinding.FragmentSettingsBinding
import com.serdararici.newsapp.ui.AdminActivity
import com.serdararici.newsapp.ui.AuthActivity

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding?= null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.cvSettings.setOnClickListener{
            //Çıkış yaprken giriş yapan kullanıcı verileri siliniyor
            val sharedPref = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.clear()  // Tüm kayıtları silmek için clear kullanabilirsin
            editor.apply()


            val intent = Intent(requireContext(), AuthActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
            Toast.makeText(
                requireContext(),
                getString(R.string.signOutMessage),
                Toast.LENGTH_LONG
            ).show()
        }
    }

}