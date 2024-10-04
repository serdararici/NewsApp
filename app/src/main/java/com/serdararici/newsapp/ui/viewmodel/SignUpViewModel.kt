package com.serdararici.newsapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.serdararici.newsapp.data.entity.User
import com.serdararici.newsapp.data.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(var urepo: UserRepo) :ViewModel() {
    fun addUser(userName:String,userMail:String,password:String,role:String) {
        urepo.addUser(userName,userMail,password,role)
    }

    fun deleteUser(userId:Int) {
        urepo.deleteUser(userId)
    }
}