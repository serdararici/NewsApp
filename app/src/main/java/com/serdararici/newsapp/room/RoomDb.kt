package com.serdararici.newsapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.serdararici.newsapp.data.entity.Etkinlik
import com.serdararici.newsapp.data.entity.User

@Database(entities = [Etkinlik::class, User::class], version = 1)
abstract class RoomDb : RoomDatabase(){
    abstract fun getEtkinlikDao() : EtkinlikDao

    abstract fun getUserDao() : UserDao

}
