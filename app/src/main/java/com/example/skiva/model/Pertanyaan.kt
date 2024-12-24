package com.example.skiva.model

data class Pertanyaan(
    val text: String,
    var isYesSelected: Boolean = false, // Status checkbox "Ya"
    var isNoSelected: Boolean = false  // Status checkbox "Tidak"
)
