package com.example.skiva.model

data class ResultScreening(
    val id: String = "",
    val userId: String = "",
    val namaPenyakit: String = "",
    val persentaseKemungkinan: Int = 0,
    val rekomendasi: String = "",
    val tanggal: String = "",
    val gambarUrl: String = ""
)
