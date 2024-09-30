package com.serdararici.newsapp.data.entity

class Duyuru(
    id: Long,
    konu: String,
    icerik: String,
    gecerlilikTarihi: String,
    val resimYolu: String,
    kullaniciAdi: String
) : Etkinlik(id, konu, icerik, gecerlilikTarihi, "duyuru",kullaniciAdi)