package com.example.skiva.model

data class Disease(
    val id_penyakit: String = "",
    val nama_penyakit: String = "",
    val kategori: String = "",
    val deskripsi_penyakit: String = "",
    val penyebab: String = "",
    val gejala: String = "",
    val pencegahan: String = "",
    val pengobatan_awal: String = "",
    val gambar_penyakit: String = ""
)
