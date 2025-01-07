package com.example.skiva.UI

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.skiva.R

class detail_dokter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_dokter)

        val nama = intent.getStringExtra("namaDokter")
        val spesialisasi = intent.getStringExtra("spesialisasi")
        val deskripsi = intent.getStringExtra("deskripsi")
        val experience = intent.getStringExtra("experience")

        findViewById<TextView>(R.id.nama).text = nama
        findViewById<TextView>(R.id.spesialisasi).text = spesialisasi
        findViewById<TextView>(R.id.deskripsi).text = deskripsi
        findViewById<TextView>(R.id.experience).text = experience

        val buttonBack: ImageButton = findViewById(R.id.button_back)
        buttonBack.setOnClickListener {
            finish()
        }
    }
}