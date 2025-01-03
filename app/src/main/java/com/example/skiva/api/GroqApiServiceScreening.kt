package com.example.skiva.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GroqApiServiceScreening {
    @Headers(
        "Authorization: Bearer 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",
        "Content-Type: application/json"
    )
    @POST("analyze") // Endpoint relatif terhadap base URL
    fun analyzeImage(@Body request: AnalyzeRequest): Call<AnalyzeResponse>
}


data class AnalyzeRequest(
    val imageBase64: String,
    val symptoms: List<String>,
    val notes: String
)

data class AnalyzeResponse(
    val disease: String,
    val percentage: Int,
    val recommendation: String
)
