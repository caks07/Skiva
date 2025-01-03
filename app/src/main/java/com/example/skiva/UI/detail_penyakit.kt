package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.skiva.R
import com.example.skiva.model.Disease
import com.google.firebase.database.FirebaseDatabase

class detail_penyakit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_penyakit)

        val diseaseId = intent.getStringExtra("DISEASE_ID") ?: return
        val databaseReference = FirebaseDatabase.getInstance("https://skiva-pab-default-rtdb.asia-southeast1.firebasedatabase.app")
            .getReference("diseases/$diseaseId")

        databaseReference.get().addOnSuccessListener { snapshot ->
            val disease = snapshot.getValue(Disease::class.java)
            disease?.let {
                findViewById<TextView>(R.id.namaPenyakit).text = it.nama_penyakit
                findViewById<TextView>(R.id.deskripsiPenyakit).text = it.deskripsi_penyakit
                findViewById<TextView>(R.id.penyebab).text = it.penyebab
                findViewById<TextView>(R.id.gejala).text = it.gejala
                findViewById<TextView>(R.id.pencegahan).text = it.pencegahan
                findViewById<TextView>(R.id.pengobatan).text = it.pengobatan_awal
            }
        }.addOnFailureListener { e ->
            Log.e("Firebase", "Error fetching disease details: ${e.message}")
        }

        val buttonBack: ImageButton = findViewById(R.id.back_button)
        buttonBack.setOnClickListener {
            val intent = Intent(this, kategori_penyakit::class.java)
            startActivity(intent)
        }
    }
}
