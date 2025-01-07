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
import com.example.skiva.model.Disease
import com.example.skiva.repository.DiseaseRepository

class kategori_penyakit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori_penyakit)

        val category = intent.getStringExtra("CATEGORY") ?: return
        val headerText: TextView = findViewById(R.id.header_text)
        headerText.text = category

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val diseaseRepository = DiseaseRepository()
        diseaseRepository.getDiseasesByCategory(category,
            onSuccess = { diseases ->
                val adapter = AdapterDisease(diseases) { disease ->
                    navigateToDetail(disease)
                }
                recyclerView.adapter = adapter
            },
            onFailure = { error ->
                Log.e("Firebase", "Error fetching diseases: $error")
            }
        )

        val buttonBack: ImageButton = findViewById(R.id.back_button)
        buttonBack.setOnClickListener {
            val intent = Intent(this, home_page::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToDetail(disease: Disease) {
        val intent = Intent(this, detail_penyakit::class.java)
        intent.putExtra("DISEASE_ID", disease.id_penyakit)
        startActivity(intent)
    }
}
