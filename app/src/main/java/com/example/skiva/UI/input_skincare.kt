package com.example.skiva.UI

import InputSkincareViewModel
import InputSkincareViewModelFactory
import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.DataSkincare
import com.example.skiva.repository.UserRepository

class input_skincare : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterInputSkincare
    private val skincareList = mutableListOf<DataSkincare>()
    private val userId: String by lazy {
        getSharedPreferences("USER_SESSION", MODE_PRIVATE).getString("USER_ID", null) ?: ""
    }

    private val viewModel: InputSkincareViewModel by viewModels {
        InputSkincareViewModelFactory(UserRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_skincare)

        recyclerView = findViewById(R.id.recyclerViewInput)
        val buttonAdd: ImageButton = findViewById(R.id.buttonAdd)
        val buttonSubmit: ImageButton = findViewById(R.id.imageButton)

        adapter = AdapterInputSkincare(skincareList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        buttonAdd.setOnClickListener {
            skincareList.add(DataSkincare(waktu = "", treatment = ""))
            adapter.notifyItemInserted(skincareList.size - 1)
        }

        buttonSubmit.setOnClickListener {
            val waktuList = mutableListOf<String>()

            if (findViewById<CheckBox>(R.id.Pagi).isChecked) waktuList.add("Pagi")
            if (findViewById<CheckBox>(R.id.Siang).isChecked) waktuList.add("Siang")
            if (findViewById<CheckBox>(R.id.Sore).isChecked) waktuList.add("Sore")
            if (findViewById<CheckBox>(R.id.Malam).isChecked) waktuList.add("Malam")

            val treatmentPagi = findViewById<EditText>(R.id.inputTrathmentPagi).text.toString()
            val treatmentSiang = findViewById<EditText>(R.id.inputTrathmentSiang).text.toString()
            val treatmentSore = findViewById<EditText>(R.id.inputTrathmentSore).text.toString()
            val treatmentMalam = findViewById<EditText>(R.id.inputTrathmentMalam).text.toString()

            val skincareList = mutableListOf<DataSkincare>()

            if (waktuList.contains("Pagi")) skincareList.add(DataSkincare(waktu = "Pagi", treatment = treatmentPagi))
            if (waktuList.contains("Siang")) skincareList.add(DataSkincare(waktu = "Siang", treatment = treatmentSiang))
            if (waktuList.contains("Sore")) skincareList.add(DataSkincare(waktu = "Sore", treatment = treatmentSore))
            if (waktuList.contains("Malam")) skincareList.add(DataSkincare(waktu = "Malam", treatment = treatmentMalam))

            viewModel.saveSkincare(userId, skincareList, {
                Toast.makeText(this, "Data skincare berhasil disimpan!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, pengingat_skincare::class.java))
                finish()
            }, {
                Toast.makeText(this, "Gagal menyimpan data skincare.", Toast.LENGTH_SHORT).show()
            })
        }

    }
}
