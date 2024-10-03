package com.serdararici.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.serdararici.newsapp.data.entity.Etkinlik
import com.serdararici.newsapp.data.repo.EtkinlikRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AdminYeniDuyuruViewModel @Inject constructor(var erepo: EtkinlikRepo) : ViewModel() {

    fun addNewAnnouncement(newAnnouncementTitle:String, newAnnouncementContent:String, newAnnouncementDate:String) {
        erepo.addNewAnnouncement(newAnnouncementTitle, newAnnouncementContent, newAnnouncementDate)
    }
}