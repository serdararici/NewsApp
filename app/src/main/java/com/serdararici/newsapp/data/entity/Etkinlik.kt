package com.serdararici.newsapp.data.entity

open class Etkinlik(
    val id: Long,
    val konu: String,
    val icerik: String,
    val gecerlilikTarihi: String,
    val tip: String,
    val kullaniciAdi: String
)