package com.example.skiva.model

data class Saran(
    var namaObat: String = "",
    var catatan: String = "",
    var linkProduk: String = "",
    var gambarSaran: String = "" // URL atau nama gambar lokal
)
