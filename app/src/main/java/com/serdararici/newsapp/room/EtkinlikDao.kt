package com.serdararici.newsapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.serdararici.newsapp.data.entity.Etkinlik

@Dao
interface EtkinlikDao {
    @Query("SELECT * FROM etkinlik WHERE tip = 'haber'")
    suspend fun getAllNews(): List<Etkinlik>

    @Query("SELECT * FROM etkinlik WHERE tip = 'duyuru'")
    suspend fun getAllDuyuru(): List<Etkinlik>

    @Query("SELECT * FROM etkinlik WHERE tip = 'haber' AND (konu LIKE '%' || :searchingWord || '%' OR icerik LIKE '%' || :searchingWord || '%')")
    suspend fun searchNews(searchingWord: String): List<Etkinlik>

    @Insert
    suspend fun addNews(haber:Etkinlik)

    @Update
    suspend fun updateNews(haber:Etkinlik)

    @Delete
    suspend fun deleteNews(haber:Etkinlik)


}