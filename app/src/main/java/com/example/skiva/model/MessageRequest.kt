package com.example.skiva.model

data class MessageRequest(
    val model: String,
    val messages: List<Message>
)

data class Message(
    val role: String,
    val content: String
)
