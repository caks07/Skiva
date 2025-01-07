package com.example.skiva.model

data class AnalyzeRequest(
    val imageBase64: String,
    val symptoms: List<String>,
    val notes: String
)

