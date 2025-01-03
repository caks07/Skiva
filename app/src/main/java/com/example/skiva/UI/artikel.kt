package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.JudulArtikel

class artikel : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<JudulArtikel>
    private lateinit var imageList: Array<Int>
    private lateinit var titleList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_artikel)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        // Dummy data for articles
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

        dataList = ArrayList()
        populateData()

        val adapter = JudulArtikelAdapter(dataList) { artikel ->
            val intent = Intent(this, desc_artikel::class.java)
            intent.putExtra("TITLE", artikel.dataTitle)
            intent.putExtra("DESCRIPTION", "Deskripsi untuk artikel: ${artikel.dataTitle}")
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }

    private fun populateData() {
        for (i in titleList.indices) {
            val artikel = JudulArtikel(imageList[i], titleList[i])
            dataList.add(artikel)
        }
    }
}
