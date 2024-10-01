package com.serdararici.newsapp.data.entity

import java.io.Serializable

open class Etkinlik(
    val id: Int,
    val konu: String,
    val icerik: String,
    val gecerlilikTarihi: String,
    val tip: String,
    val kullaniciAdi: String
) : Serializable {

}