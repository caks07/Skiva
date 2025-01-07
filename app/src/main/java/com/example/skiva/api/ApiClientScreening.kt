package com.example.skiva.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientScreening {
    private const val BASE_URL = "https://api.groq.com/openai/v1/"

    val instance: GroqApiServiceScreening by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GroqApiServiceScreening::class.java)
    }
}