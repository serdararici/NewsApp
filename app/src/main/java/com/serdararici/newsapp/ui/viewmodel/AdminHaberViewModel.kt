package com.serdararici.newsapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serdararici.newsapp.data.entity.Etkinlik
import com.serdararici.newsapp.data.repo.EtkinlikRepo
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminHaberViewModel  @Inject constructor(var erepo: EtkinlikRepo) : ViewModel() {
    var haberlerListesiLive = MutableLiveData<List<Etkinlik>>()
    init {
        getAllNews()
        haberlerListesiLive = erepo.getAllNewsLive()
    }


    fun searchNews(searchingWord: String){
        erepo.searchNews(searchingWord)
    }
    fun deleteNews(id: Int) {
        erepo.deleteNews(id)
    }
    fun getAllNews() {
        erepo.getAllNews()
    }

}