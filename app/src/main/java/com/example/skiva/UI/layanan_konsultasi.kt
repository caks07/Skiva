package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.Dokter

class layanan_konsultasi : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterDokter: AdapterDokter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan_konsultasi)

        recyclerView = findViewById(R.id.recycler_view_dokter)

        val dokterList = listOf(
            Dokter(
                nama = "Dr. Aulia Rahman",
                spesialisasi = "Dermatologi",
                deskripsi = "Spesialis dalam perawatan kulit dan kelainan dermatologi.",
                experience = "10 tahun pengalaman",
                photoUrl = "https://example.com/photo1.jpg"
            ),
            Dokter(
                nama = "Dr. Budi Santoso",
                spesialisasi = "Kesehatan Anak",
                deskripsi = "Ahli dalam kesehatan anak-anak.",
                experience = "15 tahun pengalaman",
                photoUrl = "https://example.com/photo2.jpg"
            )
        )

        adapterDokter = AdapterDokter(this, dokterList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterDokter

        val buttonChat: ImageButton = findViewById(R.id.konsultasi)
        buttonChat.setOnClickListener {
            val intent = Intent(this, Chat::class.java)
            startActivity(intent)
        }
    }
}
