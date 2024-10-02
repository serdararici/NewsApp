package com.serdararici.newsapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serdararici.newsapp.data.entity.User
import com.serdararici.newsapp.data.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(var urepo: UserRepo) : ViewModel(){
    var userLive = MutableLiveData<User>()
    init {
        userLive = urepo.getUserLiveData()
    }

    fun signInUser(userMail:String,password:String) {
        urepo.signInUser(userMail, password)
    }
}