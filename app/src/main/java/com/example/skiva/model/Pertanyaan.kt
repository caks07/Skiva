package com.example.skiva.model

data class Pertanyaan(
    val id: String,
    val text: String,
    var isYesSelected: Boolean = false,
    var isNoSelected: Boolean = false
)
