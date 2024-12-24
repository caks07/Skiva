package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.JadwalObat
import com.example.skiva.viewModel.JadwalObatViewModel
import com.example.skiva.viewModel.JadwalObatViewModelFactory
import com.example.skiva.repository.JadwalObatRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class pengingat_obat : AppCompatActivity() {
    private lateinit var viewModel: JadwalObatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengingat_obat)

        val recyclerViewPagi = findViewById<RecyclerView>(R.id.jadwalObatPagi)
        recyclerViewPagi.layoutManager = LinearLayoutManager(this)

        // Data dummy
        val dummyData = listOf(
            JadwalObat("Paracetamol", "Setelah makan", "500mg", "Tablet", "Pagi", false),
            JadwalObat("Ibuprofen", "Sebelum tidur", "200mg", "Tablet", "Malam", true),
            JadwalObat("Amoxicillin", "Sebelum makan", "250mg", "Kapsul", "Siang", false),
            JadwalObat("Vitamin C", "Setelah makan", "1000mg", "Tablet", "Pagi", true)
        )

        // Pasang adapter dengan data dummy
        recyclerViewPagi.adapter = JadwalObatAdapter(dummyData)


        // Tombol tambah obat
        val tambahObatButton: ImageButton = findViewById(R.id.tambahObat)
        tambahObatButton.setOnClickListener {
            val intent = Intent(this, input_obat::class.java)
            startActivity(intent)
        }


        // Navigasi ke halaman home page
        val buttonBack: ImageButton = findViewById(R.id.imageButtonBack)
        buttonBack.setOnClickListener {
            val intent = Intent(this, home_page::class.java)
            startActivity(intent)
        }
    }
}
