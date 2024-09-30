package com.serdararici.newsapp.data.entity

class Haber(
    id: Long,
    konu: String,
    icerik: String,
    gecerlilikTarihi: String,
    val haberLinki: String,
    kullaniciAdi: String
) : Etkinlik(id, konu, icerik, gecerlilikTarihi, "haber",kullaniciAdi)