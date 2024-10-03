package com.serdararici.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.serdararici.newsapp.data.entity.Etkinlik
import com.serdararici.newsapp.data.repo.EtkinlikRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminDuyuruGuncelleViewModel @Inject constructor(var erepo: EtkinlikRepo) : ViewModel() {

    fun updateAnnouncement(announcementId:Int, updateAnnouncementTitle:String, updateAnnouncementContent:String, updateAnnouncementDate:String) {
        erepo.updateAnnouncement(announcementId, updateAnnouncementTitle, updateAnnouncementContent, updateAnnouncementDate)
    }

}