package com.example.skiva.UI

import InputSkincareViewModel
import InputSkincareViewModelFactory
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    private lateinit var userId: String

    private val viewModel: InputSkincareViewModel by viewModels {
        InputSkincareViewModelFactory(UserRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_skincare)

        userId = getSharedPreferences("USER_SESSION", MODE_PRIVATE)
            .getString("USER_ID", null) ?: run {
            Toast.makeText(this, "User session not found. Please login again.", Toast.LENGTH_SHORT)
                .show()
            finish()
            return
        }

        recyclerView = findViewById(R.id.recyclerViewInput)
        val buttonAdd: ImageButton = findViewById(R.id.buttonAdd)
        val buttonSubmit: ImageButton = findViewById(R.id.imageButton)

        adapter = AdapterInputSkincare(skincareList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        buttonAdd.setOnClickListener {
            try {
                skincareList.add(DataSkincare(waktu = "", treatment = ""))
                adapter.notifyItemInserted(skincareList.size - 1)
                Log.d("InputSkincare", "Item added: ${skincareList.last()}")
            } catch (e: Exception) {
                Log.e("InputSkincare", "Error saat menambahkan item: ${e.message}")
            }
        }


        buttonSubmit.setOnClickListener {
            if (skincareList.any { it.treatment.isBlank() }) {
                Toast.makeText(this, "Please fill all treatments.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.saveSkincare(userId, skincareList, {
                Toast.makeText(this, "Skincare berhasil disimpan!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, pengingat_skincare::class.java))
                finish()
            }, { error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            })
        }
    }
}
