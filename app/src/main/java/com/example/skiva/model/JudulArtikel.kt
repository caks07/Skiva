package com.example.skiva.model

data class JudulArtikel(
    val dataImage: Int,
    val dataTitle: String,
    var description: String = "" // Default empty for compatibility
)
