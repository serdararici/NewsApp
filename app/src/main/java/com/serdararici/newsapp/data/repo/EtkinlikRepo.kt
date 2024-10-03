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
    var duyurularListesiLive:MutableLiveData<List<Etkinlik>>
    init {
        haberlerListesiLive = MutableLiveData()
        duyurularListesiLive = MutableLiveData()
    }

    fun getAllNewsLive() : MutableLiveData<List<Etkinlik>>{
        return haberlerListesiLive
    }
    fun getAllAnnouncementLive() : MutableLiveData<List<Etkinlik>>{
        return duyurularListesiLive
    }

    fun addNewNews(newNewsTitle:String, newNewsContent:String, newNewsDate:String, newNewsImage:String, newNewsUserName:String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val newHaber = Etkinlik(0,newNewsTitle,newNewsContent,newNewsDate,newNewsImage,"haber",newNewsUserName,"")
            edao.addNews(newHaber)
        }
    }
    fun addNewAnnouncement(newAnnouncementTitle:String, newAnnouncementContent:String, newAnnouncementDate:String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val newAnnouncement = Etkinlik(0,newAnnouncementTitle,newAnnouncementContent,newAnnouncementDate,"","duyuru","serdararici","")
            edao.addAnnouncement(newAnnouncement)
        }
    }

    fun updateNews(newsId: Int, updateNewsTitle:String, updateNewsContent:String, updateNewsDate:String,newNewsImage:String, newNewsUserName:String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val updateHaber = Etkinlik(newsId,updateNewsTitle,updateNewsContent,updateNewsDate,newNewsImage,"haber",newNewsUserName,"")
            edao.updateNews(updateHaber)
        }
    }
    fun updateAnnouncement(announcementId: Int, updateAnnouncementTitle:String, updateAnnouncementContent:String, updateAnnouncementDate:String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val updateAnnouncement = Etkinlik(announcementId,updateAnnouncementTitle,updateAnnouncementContent,updateAnnouncementDate,"","duyuru","serdararici","")
            edao.updateAnnouncement(updateAnnouncement)
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
    fun deleteAnnouncement(id: Int) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val deleteAnnouncement = Etkinlik(id, "", "", "", "", "duyuru", "", "")
            edao.deleteAnnouncement(deleteAnnouncement)
            getAllAnnouncement()
        }
    }

    fun getAllNews() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            haberlerListesiLive.value = edao.getAllNews()
        }
    }
    fun getAllAnnouncement() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            duyurularListesiLive.value = edao.getAllAnnouncement()
        }
    }
}