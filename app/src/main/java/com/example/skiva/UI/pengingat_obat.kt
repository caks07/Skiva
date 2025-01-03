package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.utils.SessionManager
import com.example.skiva.viewModel.JadwalObatViewModel
import com.example.skiva.model.JadwalObat
import com.example.skiva.viewModel.JadwalObatViewModelFactory
import com.example.skiva.repository.JadwalObatRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class pengingat_obat : AppCompatActivity() {
    private lateinit var viewModel: JadwalObatViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengingat_obat)

        progressBar = findViewById(R.id.progressBar)

        val sessionManager = SessionManager(this)
        val userId = sessionManager.getUserId()

        if (userId == null) {
            Toast.makeText(this, "Sesi pengguna tidak ditemukan. Silakan login kembali.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, login::class.java))
            finish()
            return
        }

        viewModel = ViewModelProvider(
            this,
            JadwalObatViewModelFactory(JadwalObatRepository())
        )[JadwalObatViewModel::class.java]

        val recyclerViewPagi = findViewById<RecyclerView>(R.id.jadwalObatPagi)
        val recyclerViewSiang = findViewById<RecyclerView>(R.id.jadwalObatSiang)
        val recyclerViewSore = findViewById<RecyclerView>(R.id.jadwalObatSore)
        val recyclerViewMalam = findViewById<RecyclerView>(R.id.jadwalObatMalam)

        recyclerViewPagi.layoutManager = LinearLayoutManager(this)
        recyclerViewSiang.layoutManager = LinearLayoutManager(this)
        recyclerViewSore.layoutManager = LinearLayoutManager(this)
        recyclerViewMalam.layoutManager = LinearLayoutManager(this)

        fun setupAdapter(recyclerView: RecyclerView, data: List<JadwalObat>) {
            recyclerView.adapter = AdapterResep(data) { updateProgressBar() }
        }

        viewModel.jadwalObatPagi.observe(this) { data ->
            setupAdapter(recyclerViewPagi, data)
        }

        viewModel.jadwalObatSiang.observe(this) { data ->
            setupAdapter(recyclerViewSiang, data)
        }

        viewModel.jadwalObatSore.observe(this) { data ->
            setupAdapter(recyclerViewSore, data)
        }

        viewModel.jadwalObatMalam.observe(this) { data ->
            setupAdapter(recyclerViewMalam, data)
        }

        viewModel.loadJadwalObat(userId)

        val tambahObatButton: ImageButton = findViewById(R.id.tambahObat)
        tambahObatButton.setOnClickListener {
            val intent = Intent(this, input_obat::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

        val bulanTextView: TextView = findViewById(R.id.Bulan)
        val hariTextView: TextView = findViewById(R.id.Hari)
        val tanggalTextView: TextView = findViewById(R.id.Tanggal)

        val calendar = Calendar.getInstance()
        val bulanFormat = SimpleDateFormat("MMMM", Locale.getDefault())
        val hariFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val tanggalFormat = SimpleDateFormat("dd", Locale.getDefault())

        bulanTextView.text = bulanFormat.format(calendar.time)
        hariTextView.text = hariFormat.format(calendar.time)
        tanggalTextView.text = tanggalFormat.format(calendar.time)

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
        val totalItems = viewModel.jadwalObatPagi.value?.size.orZero() +
                viewModel.jadwalObatSiang.value?.size.orZero() +
                viewModel.jadwalObatSore.value?.size.orZero() +
                viewModel.jadwalObatMalam.value?.size.orZero()

        val checkedItems = listOf(
            viewModel.jadwalObatPagi.value.orEmpty(),
            viewModel.jadwalObatSiang.value.orEmpty(),
            viewModel.jadwalObatSore.value.orEmpty(),
            viewModel.jadwalObatMalam.value.orEmpty()
        ).flatten().count { it.isChecked }

        val progress = if (totalItems > 0) (checkedItems * 100) / totalItems else 0
        progressBar.progress = progress
    }

    private fun Int?.orZero() = this ?: 0
}
