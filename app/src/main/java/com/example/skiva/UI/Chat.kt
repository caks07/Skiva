package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.viewModel.ChatMessage
import com.example.skiva.viewModel.ChatViewModel

class Chat : AppCompatActivity() {
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var chatViewModel: ChatViewModel
    private val chatMessages = mutableListOf<ChatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Inisialisasi RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewChat)
        val inputMessage = findViewById<EditText>(R.id.middleInputBox)
        val sendButton = findViewById<ImageButton>(R.id.sendButton)

        chatAdapter = ChatAdapter(chatMessages)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = chatAdapter

        // ViewModel
        chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]

        // Observasi data dari ViewModel
        chatViewModel.messages.observe(this) {updatedMessages ->
            chatMessages.clear()
            chatMessages.addAll(updatedMessages)
            chatAdapter.notifyDataSetChanged()
            recyclerView.scrollToPosition(chatMessages.size - 1) // Scroll ke bawah
        }

        // Klik tombol sendButton untuk kirim pesan
        sendButton.setOnClickListener {
            val userMessage = inputMessage.text.toString()
            if (userMessage.isNotBlank()) {
                Log.d("ChatActivity", "Send button clicked. Message: $userMessage")
                chatViewModel.sendMessage(userMessage)
                inputMessage.text.clear()
            } else {
                Log.d("ChatActivity", "Message box is empty")
            }
        }

        chatViewModel.messages.observe(this) { updatedMessages ->
            Log.d("ChatActivity", "Messages updated: ${updatedMessages.size}")
            chatMessages.clear()
            chatMessages.addAll(updatedMessages)
            chatAdapter.notifyDataSetChanged()
            recyclerView.scrollToPosition(chatMessages.size - 1)
        }

        // Navigasi ke halaman badge
        val buttonBack: ImageButton = findViewById(R.id.back)
        buttonBack.setOnClickListener {
            val intent = Intent(this, layanan_konsultasi::class.java)
            startActivity(intent)
        }
    }
}
