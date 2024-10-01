package com.serdararici.newsapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.serdararici.newsapp.R
import com.serdararici.newsapp.databinding.ActivityAdminBinding
import com.serdararici.newsapp.databinding.ActivityMainBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

         */

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewAdmin) as NavHostFragment

        NavigationUI.setupWithNavController(binding.BottomNavAdmin, navHostFragment.navController)

    }
}