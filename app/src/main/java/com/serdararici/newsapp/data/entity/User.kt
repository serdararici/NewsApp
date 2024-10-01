package com.serdararici.newsapp.data.entity

data class User (
    val id: Int,
    val userName: String,
    val password: String,
    val isAdmin: Boolean // true ise admin, false ise normal kullanıcı
)
