package com.serdararici.newsapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serdararici.newsapp.data.entity.Etkinlik
import com.serdararici.newsapp.data.repo.EtkinlikRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminDuyuruViewModel @Inject constructor(var erepo: EtkinlikRepo) : ViewModel() {
    var duyurularListesiLive = MutableLiveData<List<Etkinlik>>()
    init {
        getAllAnnouncement()
        duyurularListesiLive = erepo.getAllAnnouncementLive()
    }

    fun deleteAnnouncement(id: Int) {
        erepo.deleteAnnouncement(id)
    }

    fun getAllAnnouncement() {
        erepo.getAllAnnouncement()
    }
}