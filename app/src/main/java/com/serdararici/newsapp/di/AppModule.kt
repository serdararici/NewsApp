package com.serdararici.newsapp.di

import android.content.Context
import androidx.room.Room
import com.serdararici.newsapp.data.entity.User
import com.serdararici.newsapp.data.repo.EtkinlikRepo
import com.serdararici.newsapp.data.repo.UserRepo
import com.serdararici.newsapp.room.EtkinlikDao
import com.serdararici.newsapp.room.RoomDb
import com.serdararici.newsapp.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideEtkinlikRepo(edao:EtkinlikDao) : EtkinlikRepo {
        return EtkinlikRepo(edao)
    }

    @Provides
    @Singleton
    fun provideEtkinlikDao(@ApplicationContext context: Context) : EtkinlikDao {
        val db = Room.databaseBuilder(context, RoomDb::class.java, "news.sqlite")
            .createFromAsset("news.sqlite").build()
        return db.getEtkinlikDao()
    }

    @Provides
    @Singleton
    fun provideUserRepo(udao:UserDao) : UserRepo {
        return UserRepo(udao)
    }

    @Provides
    @Singleton
    fun provideUserDao(@ApplicationContext context: Context) : UserDao {
        val db = Room.databaseBuilder(context, RoomDb::class.java, "news.sqlite")
            .createFromAsset("news.sqlite").build()
        return db.getUserDao()
    }
}