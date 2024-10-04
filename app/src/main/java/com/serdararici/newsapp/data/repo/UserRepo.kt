package com.serdararici.newsapp.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.serdararici.newsapp.data.entity.Etkinlik
import com.serdararici.newsapp.data.entity.User
import com.serdararici.newsapp.room.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepo(var udao:UserDao) {
    var userLive:MutableLiveData<User>
    init {
        userLive = MutableLiveData()
    }

    fun getUserLiveData() : MutableLiveData<User> {
        return userLive
    }

    fun addUser(userName:String,userMail:String,password:String,role:String) {
        Log.e("signUp", "Kayıt başarılı ${userName}")
        val job = CoroutineScope(Dispatchers.Main).launch {
            val newUser = User(0,userName,userMail,password,role)
            udao.addUser(newUser)
        }
    }

    fun deleteUser(userId:Int) {
        Log.e("deleteUser", "User silindi: ${userId}")
        val job = CoroutineScope(Dispatchers.Main).launch {
            val deleteUser = User(userId, "", "", "", "")
            udao.deleteUser(deleteUser)
        }
    }

    fun signInUser(userMail:String,password: String) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            userLive.postValue(udao.getUser(userMail,password))
            //userLive.value = udao.getUser(userMail,password)
        }
    }
}