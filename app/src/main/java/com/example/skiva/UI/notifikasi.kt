package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.Notifikasi

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

        val buttonBack: ImageButton = findViewById(R.id.b_back)
        buttonBack.setOnClickListener {
            val intent = Intent(this, home_page::class.java)
            startActivity(intent)
        }
    }
}
