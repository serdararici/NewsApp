package com.serdararici.newsapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serdararici.newsapp.data.entity.Etkinlik
import com.serdararici.newsapp.data.repo.EtkinlikRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HaberViewModel @Inject constructor(var erepo: EtkinlikRepo) : ViewModel() {

    var haberlerListesiLive = MutableLiveData<List<Etkinlik>>()
    init {
        getAllNews()
        haberlerListesiLive = erepo.getAllNewsLive()
    }

    fun searchNews(searchingWord: String){
        erepo.searchNews(searchingWord)
    }
    fun getAllNews() {
        erepo.getAllNews()
    }
}