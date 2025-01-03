package com.example.skiva.UI

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.skiva.R

class desc_artikel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desc_artikel)

        // Ambil data dari Intent
        val title = intent.getStringExtra("TITLE") ?: "No Title"
        val description = intent.getStringExtra("DESCRIPTION") ?: "No Description"

        // Inisialisasi view
        val titleTextView: TextView = findViewById(R.id.title)
        val descriptionTextView: TextView = findViewById(R.id.deskripsiArtikel)
        val backButton: ImageButton = findViewById(R.id.backButton)

        // Set data ke TextView
        titleTextView.text = title
        descriptionTextView.text = description

        // Fungsi tombol kembali
        backButton.setOnClickListener {
            finish()
        }
    }
}
