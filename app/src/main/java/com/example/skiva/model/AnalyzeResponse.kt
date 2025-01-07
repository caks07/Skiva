package com.example.skiva.model

data class AnalyzeResponse(
    val disease: String,
    val percentage: Int,
    val recommendation: String
)