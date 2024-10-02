package com.serdararici.newsapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.serdararici.newsapp.data.repo.EtkinlikRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminHaberGuncelleViewModel @Inject constructor(var erepo: EtkinlikRepo) : ViewModel() {

    fun updateNews(newsId: Int, updateNewsTitle:String, updateNewsContent:String) {
        erepo.updateNews(newsId,updateNewsTitle, updateNewsContent)
    }

}