package com.serdararici.newsapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "etkinlik")
data class Etkinlik(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") @NotNull
    var id: Int,
    @ColumnInfo(name = "konu") @NotNull
    var konu: String,
    @ColumnInfo(name = "icerik") @NotNull
    var icerik: String,
    @ColumnInfo(name = "gecerlilikTarihi") @NotNull
    var gecerlilikTarihi: String,
    @ColumnInfo(name = "resim") @NotNull
    var resim: String,
    @ColumnInfo(name = "tip") @NotNull
    var tip: String,
    @ColumnInfo(name = "kullaniciAdi") @NotNull
    var kullaniciAdi: String,
    @ColumnInfo(name = "haberLinki") @NotNull
    var haberLinki: String,
) : Serializable {


}

