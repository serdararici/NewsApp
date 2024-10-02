package com.serdararici.newsapp.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.serdararici.newsapp.data.entity.Etkinlik
import com.serdararici.newsapp.room.EtkinlikDao
import com.serdararici.newsapp.room.RoomDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EtkinlikRepo (var edao:EtkinlikDao){
    var haberlerListesiLive:MutableLiveData<List<Etkinlik>>
    init {
        haberlerListesiLive = MutableLiveData()
    }

    fun getAllNewsLive() : MutableLiveData<List<Etkinlik>>{
        return haberlerListesiLive
    }

    fun addNewNews(newNewsTitle:String, newNewsContent:String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val newHaber = Etkinlik(0,newNewsTitle,newNewsContent,"02.10.2024","","haber","serdararici","")
            edao.addNews(newHaber)
        }
    }
    fun updateNews(newsId: Int, updateNewsTitle:String, updateNewsContent:String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val updateHaber = Etkinlik(newsId,updateNewsTitle,updateNewsContent,"02.10.2024","","haber","serdararici","")
            edao.updateNews(updateHaber)
        }
    }
    fun searchNews(searchingWord: String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            haberlerListesiLive.value = edao.searchNews(searchingWord)
        }
    }
    fun deleteNews(id: Int) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val deleteHaber = Etkinlik(id, "", "", "", "", "haber", "", "")
            edao.deleteNews(deleteHaber)
            getAllNews()
        }
    }
    fun getAllNews() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            haberlerListesiLive.value = edao.getAllNews()
        }
    }
}