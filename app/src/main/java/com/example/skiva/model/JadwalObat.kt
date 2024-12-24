package com.example.skiva.model

data class JadwalObat(
    val namaObat: String = "",
    val catatan: String = "",
    val dosis: String = "",
    val jenis: String = "",
    val waktu: String = "",
    var isChecked: Boolean = false
)

