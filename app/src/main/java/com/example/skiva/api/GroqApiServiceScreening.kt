package com.example.skiva.api

import com.example.skiva.model.AnalyzeRequest
import com.example.skiva.model.AnalyzeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GroqApiServiceScreening {
    @Headers(
        "Authorization: Bearer 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",
        "Content-Type: application/json"
    )
    @POST("chat/completions") // Endpoint relatif terhadap base URL
    fun analyzeImage(@Body request: AnalyzeRequest): Call<AnalyzeResponse>
}

