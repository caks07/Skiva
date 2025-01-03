package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.JudulArtikel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class home_page : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<JudulArtikel>
    private lateinit var imageList: Array<Int>
    private lateinit var titleList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        // Inisialisasi UI
        val textViewName: TextView = findViewById(R.id.textViewName)
        recyclerView = findViewById(R.id.recyclerView)

        // Ambil UID pengguna
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId.isNullOrEmpty()) {
            textViewName.text = "Hai, User"
            Log.e("FirebaseDebug", "User ID is null or empty")
            return
        }

        // Ambil nama pengguna dari Firebase
        val databaseReference = FirebaseDatabase.getInstance("https://skiva-pab-default-rtdb.asia-southeast1.firebasedatabase.app")
            .getReference("users/$userId")
        databaseReference.child("name").get().addOnSuccessListener { snapshot ->
            val name = snapshot.value as? String ?: "User"
            textViewName.text = "Hai, $name"
            Log.d("FirebaseDebug", "Nama ditemukan di Firebase: $name")
        }.addOnFailureListener { e ->
            textViewName.text = "Hai, User"
            Log.e("FirebaseDebug", "Gagal mengambil nama dari Firebase", e)
        }

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        imageList = arrayOf(
            R.drawable.headache,
            R.drawable.sore_throat,
            R.drawable.demam,
            R.drawable.batuk,
            R.drawable.sesak_napas,
            R.drawable.gabisa_nyium_bau
        )

        titleList = arrayOf(
            "Rahasia Kulit Sehat",
            "Penyakit Kulit Umum",
            "Tren Skincare Alami",
            "Mengenal Eksim Lebih Dalam",
            "Jerawat di Usia Dewasa",
            "Kulit Kusam vs Cerah"
        )

        dataList = arrayListOf()
        getData()

        // Navigasi ke halaman lain
        setupNavigation()
    }

    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = JudulArtikel(imageList[i], titleList[i])
            dataList.add(dataClass)
        }

        recyclerView.adapter = JudulArtikelAdapter(dataList) { artikel ->
            val intent = Intent(this, desc_artikel::class.java)
            intent.putExtra("TITLE", artikel.dataTitle)
            intent.putExtra("DESCRIPTION", "Deskripsi untuk artikel: ${artikel.dataTitle}")
            startActivity(intent)
        }
    }


    private fun navigateToCategory(category: String) {
        val intent = Intent(this, kategori_penyakit::class.java)
        intent.putExtra("CATEGORY", category)
        startActivity(intent)
    }

    private fun setupNavigation() {

        val imageWajah: ImageButton = findViewById(R.id.imageWajah)
        val imageMenular: ImageButton = findViewById(R.id.imageMenular)
        val imageTidakMenular: ImageButton = findViewById(R.id.imageTidakMenular)
        val imageLainnya: ImageButton = findViewById(R.id.imageLainnya)

        imageWajah.setOnClickListener { navigateToCategory("Wajah") }
        imageMenular.setOnClickListener { navigateToCategory("Menular") }
        imageTidakMenular.setOnClickListener { navigateToCategory("Tidak Menular") }
        imageLainnya.setOnClickListener { navigateToCategory("Lainnya") }


        val pengingatButton: ImageButton = findViewById(R.id.imageButtonPengingat)
        pengingatButton.setOnClickListener {
            val intent = Intent(this, pengingat_obat::class.java)
            startActivity(intent)
        }

        val buttonScreening: ImageButton = findViewById(R.id.buttonScreening)
        buttonScreening.setOnClickListener {
            val intent = Intent(this, screening::class.java)
            startActivity(intent)
        }

        val buttonBadge: ImageButton = findViewById(R.id.ButtonBadge)
        buttonBadge.setOnClickListener {
            val intent = Intent(this, badge::class.java)
            startActivity(intent)
        }

        val buttonChat: ImageButton = findViewById(R.id.konsultasi)
        buttonChat.setOnClickListener {
            val intent = Intent(this, layanan_konsultasi::class.java)
            startActivity(intent)
        }

        val buttonProfil: ImageButton = findViewById(R.id.b_profile)
        buttonProfil.setOnClickListener {
            val intent = Intent(this, profile::class.java)
            startActivity(intent)
        }

        val buttonLaporan: ImageButton = findViewById(R.id.laporan)
        buttonLaporan.setOnClickListener {
            val intent = Intent(this, laporan::class.java)
            startActivity(intent)
        }
        val buttonPromo: ImageButton = findViewById(R.id.imageButton11)
        buttonPromo.setOnClickListener {
            val intent = Intent(this, promo::class.java)
            startActivity(intent)
        }

        val buttonSkincare: ImageButton = findViewById(R.id.skincare)
        buttonSkincare.setOnClickListener {
            val intent = Intent(this, pengingat_skincare::class.java)
            startActivity(intent)
        }

        val buttonArtikel: ImageButton = findViewById(R.id.link_selengkapnya)
        buttonArtikel.setOnClickListener {
            val intent = Intent(this, artikel::class.java)
            startActivity(intent)
        }


    }
}
