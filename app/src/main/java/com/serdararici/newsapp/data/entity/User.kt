package com.serdararici.newsapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId") @NotNull
    val userId: Int,
    @ColumnInfo(name = "userName") @NotNull
    val userName: String,
    @ColumnInfo(name = "userMail") @NotNull
    val userMail: String,
    @ColumnInfo(name = "password") @NotNull
    val password: String,
    @ColumnInfo(name = "role") @NotNull
    val role: String
) : Serializable {

}
