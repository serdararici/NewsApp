package com.serdararici.newsapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.serdararici.newsapp.data.repo.EtkinlikRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminYeniHaberViewModel  @Inject constructor(var erepo: EtkinlikRepo) : ViewModel() {

    fun addNewNews(newNewsTitle:String, newNewsContent:String) {
        erepo.addNewNews(newNewsTitle,newNewsContent)
    }
}