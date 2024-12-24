package com.example.skiva.UI

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.Penyakit

class kategori_penyakit : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var penyakitAdapter: PenyakitAdapter
    private lateinit var penyakitList: List<Penyakit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori_penyakit)

        // Atur header text
        val headerText: TextView = findViewById(R.id.header_text)
        headerText.text = "Kulit Wajah"

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        // Data dummy
        penyakitList = listOf(
            Penyakit("Milia", "Ruam kecil pada kulit", R.drawable.gambar_penyakit, "Kulit Wajah"),
            Penyakit("Jerawat", "Peradangan pori-pori kulit", R.drawable.gambar_penyakit, "Kulit Wajah"),
            Penyakit("Eksim", "Ruam merah disertai rasa gatal", R.drawable.gambar_penyakit, "Kulit Wajah")
        )

        // Atur adapter
        penyakitAdapter = PenyakitAdapter(penyakitList)
        recyclerView.adapter = penyakitAdapter
    }
}
