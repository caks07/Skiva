package com.example.skiva.UI

import InputObatViewModel
import InputObatViewModelFactory
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.DataObat
import com.example.skiva.repository.UserRepository
import java.util.Calendar

class input_obat : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InputObatAdapter
    private val resepObatList = mutableListOf<DataObat>()
    private lateinit var userId: String

    private val viewModel: InputObatViewModel by viewModels {
        InputObatViewModelFactory(UserRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_obat)

        // Validasi sesi pengguna
        userId = getSharedPreferences("USER_SESSION", MODE_PRIVATE)
            .getString("USER_ID", null) ?: run {
            Toast.makeText(this, "User session not found. Please login again.", Toast.LENGTH_SHORT)
                .show()
            startActivity(Intent(this, login::class.java))
            finish()
            return
        }

        recyclerView = findViewById(R.id.recyclerView)
        val buttonAdd: ImageButton = findViewById(R.id.buttonAdd)
        val buttonSubmit: ImageButton = findViewById(R.id.imageButton)

        adapter = InputObatAdapter(resepObatList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        buttonAdd.setOnClickListener {
            resepObatList.add(DataObat("", "", "", "", "", ""))
            adapter.notifyItemInserted(resepObatList.size - 1)
        }

        buttonSubmit.setOnClickListener {
            val waktuList = mutableListOf<String>()

            // Ambil data dari CheckBox
            if (findViewById<CheckBox>(R.id.checkBoxPagi).isChecked) waktuList.add("Pagi")
            if (findViewById<CheckBox>(R.id.checkBoxSiang).isChecked) waktuList.add("Siang")
            if (findViewById<CheckBox>(R.id.checkBoxSore).isChecked) waktuList.add("Sore")
            if (findViewById<CheckBox>(R.id.checkBoxMalam).isChecked) waktuList.add("Malam")

            if (waktuList.isEmpty()) {
                Toast.makeText(this, "Pilih minimal satu waktu untuk jadwal obat", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simpan data obat ke database
            viewModel.saveObat(
                userId,
                resepObatList,
                waktuList = waktuList,
                onSuccess = {
                    Toast.makeText(this, "Obat berhasil disimpan!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, pengingat_obat::class.java))
                    finish()
                },
                onFailure = { errorMessage ->
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}
