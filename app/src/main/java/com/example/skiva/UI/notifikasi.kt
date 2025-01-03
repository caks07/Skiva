package com.example.skiva.UI

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R

class notifikasi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notifikasi)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdapterNotifikasi(
            listOf(
                Notifikasi("Minum Obat A", "Minum sebelum makan yaa", "12.00"),
                Notifikasi("Minum Obat B", "Minum sesudah makan yaa", "13.00"),
                Notifikasi("Minum Obat C", "Minum sebelum tidur", "21.00")
            )
        )
    }
}

data class Notifikasi(val title: String, val description: String, val time: String)
