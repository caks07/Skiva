package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.skiva.R

class detail_laporan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_laporan)

        // Ambil data dari intent
        val namaDokter = intent.getStringExtra("NAMA_DOKTER")
        val tanggal = intent.getStringExtra("TANGGAL")

        // Set data ke TextView atau komponen lainnya
        findViewById<TextView>(R.id.textView).text = "Detail Laporan"
        findViewById<TextView>(R.id.NamaDoker).text = namaDokter
        findViewById<TextView>(R.id.Tanggal).text = "Tanggal: $tanggal"

        val buttonBack: ImageButton = findViewById(R.id.b_back)
        buttonBack.setOnClickListener {
            finish()
        }

    }
}