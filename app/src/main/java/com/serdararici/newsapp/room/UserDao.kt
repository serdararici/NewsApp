package com.serdararici.newsapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.serdararici.newsapp.data.entity.Etkinlik
import com.serdararici.newsapp.data.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE userMail = :email AND password = :password LIMIT 1")
    fun getUser(email: String, password: String): User?

    @Insert
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}