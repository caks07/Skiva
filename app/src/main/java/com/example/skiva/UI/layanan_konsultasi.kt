package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.adapter.NavigationController
import com.example.skiva.model.Dokter

class layanan_konsultasi : AppCompatActivity() {

    private lateinit var recyclerViewDokter: RecyclerView
    private lateinit var recyclerViewTempat: RecyclerView
    private lateinit var adapterDokter: AdapterDokter
    private lateinit var adapterTempat: AdapterTempat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan_konsultasi)

        // Inisialisasi RecyclerView
        recyclerViewDokter = findViewById(R.id.recycler_view_dokter)
        recyclerViewTempat = findViewById(R.id.recycler_view_tempat)

        // Data dokter
        val dokterList = listOf(
            Dokter(
                nama = "Dr. Aulia Rahman",
                spesialisasi = "Dermatologi",
                deskripsi = "Spesialis dalam perawatan kulit dan kelainan dermatologi.",
                experience = "10 tahun pengalaman",
                photoUrl = "app/src/main/res/drawable/doker1.jpg"
            ),
            Dokter(
                nama = "Dr. Budi Santoso",
                spesialisasi = "Kesehatan Anak",
                deskripsi = "Ahli dalam kesehatan anak-anak.",
                experience = "15 tahun pengalaman",
                photoUrl = "app/src/main/res/drawable/doker2.jpg"
            )
        )

        // Data tempat
        val tempatList = listOf("Jogja", "Sleman", "Bantul")

        // Atur adapter untuk dokter
        adapterDokter = AdapterDokter(this, dokterList)
        recyclerViewDokter.layoutManager = LinearLayoutManager(this)
        recyclerViewDokter.adapter = adapterDokter

        // Atur adapter untuk tempat
        adapterTempat = AdapterTempat(tempatList)
        recyclerViewTempat.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewTempat.adapter = adapterTempat

        // Tombol konsultasi
        val buttonChat: ImageButton = findViewById(R.id.konsultasi)
        buttonChat.setOnClickListener {
            val intent = Intent(this, Chat::class.java)
            startActivity(intent)
        }

        val buttonBack: ImageButton = findViewById(R.id.imageButtonBack)
        buttonBack.setOnClickListener {
            val intent = Intent(this, home_page::class.java)
            startActivity(intent)
        }

        val buttonNotif: ImageButton = findViewById(R.id.imageButtonNotif)
        buttonNotif.setOnClickListener {
            val intent = Intent(this, notifikasi::class.java)
            startActivity(intent)
        }

        val navigationView = findViewById<View>(R.id.header_shortcut_include)
        NavigationController(this, navigationView)
    }
}
