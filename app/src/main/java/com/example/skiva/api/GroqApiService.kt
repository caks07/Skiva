package com.example.skiva.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import com.example.skiva.model.MessageRequest
import com.example.skiva.model.MessageResponse

interface
GroqApiService {
    @Headers(
        "Authorization: Bearer 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",
        "Content-Type: application/json"
    )
    @POST("chat/completions") // Pastikan endpoint benar
    fun sendMessage(@Body request: MessageRequest): Call<MessageResponse>
}
