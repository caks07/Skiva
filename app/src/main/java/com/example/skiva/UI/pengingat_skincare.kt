package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.adapter.NavigationController
import com.example.skiva.model.JadwalSkincare
import com.example.skiva.model.Saran
import com.example.skiva.repository.JadwalSkincareRepository
import com.example.skiva.utils.SessionManager
import com.example.skiva.viewModel.JadwalSkincareViewModel
import com.example.skiva.viewModel.JadwalSkincareViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class pengingat_skincare : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private val viewModel: JadwalSkincareViewModel by viewModels {
        JadwalSkincareViewModelFactory(JadwalSkincareRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengingat_skincare)

        progressBar = findViewById(R.id.progressBar)

        val sessionManager = SessionManager(this)
        val userId = sessionManager.getUserId()

        if (userId == null) {
            Toast.makeText(this, "Sesi pengguna tidak ditemukan. Silakan login kembali.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, login::class.java))
            finish()
            return
        }

        val navigationView = findViewById<View>(R.id.header_shortcut_include)
        NavigationController(this, navigationView)

// Setup RecyclerView for Saran with Dummy Data
        val recyclerViewSaran = findViewById<RecyclerView>(R.id.saran)
        recyclerViewSaran.layoutManager = LinearLayoutManager(this)

        val dummySaranList = listOf(
            Saran(
                namaObat = "Moisturizer A",
                catatan = "Gunakan setiap pagi setelah mandi.",
                linkProduk = "https://example.com/product/moisturizer-a",
                gambarSaran = "https://via.placeholder.com/150"
            ),
            Saran(
                namaObat = "Sunscreen B",
                catatan = "Aplikasikan sebelum keluar rumah.",
                linkProduk = "https://example.com/product/sunscreen-b",
                gambarSaran = "https://via.placeholder.com/150"
            ),
            Saran(
                namaObat = "Serum C",
                catatan = "Gunakan sebelum tidur untuk hasil maksimal.",
                linkProduk = "https://example.com/product/serum-c",
                gambarSaran = "https://via.placeholder.com/150"
            )
        )

        recyclerViewSaran.adapter = AdapterSaran(dummySaranList)


        val recyclerViewPagi = findViewById<RecyclerView>(R.id.jadwalObatPagi)
        val recyclerViewSiang = findViewById<RecyclerView>(R.id.jadwalObatSiang)
        val recyclerViewSore = findViewById<RecyclerView>(R.id.jadwalObatSore)
        val recyclerViewMalam = findViewById<RecyclerView>(R.id.jadwalObatMalam)

        recyclerViewPagi.layoutManager = LinearLayoutManager(this)
        recyclerViewSiang.layoutManager = LinearLayoutManager(this)
        recyclerViewSore.layoutManager = LinearLayoutManager(this)
        recyclerViewMalam.layoutManager = LinearLayoutManager(this)

        fun setupAdapter(recyclerView: RecyclerView, data: List<JadwalSkincare>) {
            recyclerView.adapter = AdapterJadwalSkincare(data) { updateProgressBar() }
        }

        viewModel.jadwalPagi.observe(this) { data ->
            setupAdapter(recyclerViewPagi, data)
        }

        viewModel.jadwalSiang.observe(this) { data ->
            setupAdapter(recyclerViewSiang, data)
        }

        viewModel.jadwalSore.observe(this) { data ->
            setupAdapter(recyclerViewSore, data)
        }

        viewModel.jadwalMalam.observe(this) { data ->
            setupAdapter(recyclerViewMalam, data)
        }

        viewModel.loadJadwalSkincare(userId)

        val tambahSkincareButton: ImageButton = findViewById(R.id.tambahObat)
        tambahSkincareButton.setOnClickListener {
            val intent = Intent(this, input_skincare::class.java)
            intent.putExtra("USER_ID", userId)
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
    }

    private fun updateProgressBar() {
        val totalItems = viewModel.jadwalPagi.value?.size.orZero() +
                viewModel.jadwalSiang.value?.size.orZero() +
                viewModel.jadwalSore.value?.size.orZero() +
                viewModel.jadwalMalam.value?.size.orZero()

        val checkedItems = listOf(
            viewModel.jadwalPagi.value.orEmpty(),
            viewModel.jadwalSiang.value.orEmpty(),
            viewModel.jadwalSore.value.orEmpty(),
            viewModel.jadwalMalam.value.orEmpty()
        ).flatten().count { it.isChecked }

        val progress = if (totalItems > 0) (checkedItems * 100) / totalItems else 0
        progressBar.progress = progress
        findViewById<TextView>(R.id.progressPercentage).text = "$progress%"
    }



    private fun Int?.orZero() = this ?: 0
}
