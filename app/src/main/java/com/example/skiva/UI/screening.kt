package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.Pertanyaan

class screening : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var nextButton: ImageButton
    private lateinit var sectionTitle: TextView

    private val sections = listOf(
        "1. Gejala Umum",
        "2. Gejala Khusus",
        "3. Riwayat Kesehatan"
    )

    private val questionsPerSection = listOf(
        listOf(
            Pertanyaan("Apakah Anda merasa demam?"),
            Pertanyaan("Apakah Anda merasa batuk kering?"),
            Pertanyaan("Apakah Anda merasa sesak napas?")
        ),
        listOf(
            Pertanyaan("Apakah Anda merasa gatal-gatal?"),
            Pertanyaan("Apakah Anda memiliki ruam pada kulit?"),
            Pertanyaan("Apakah ada luka yang tidak sembuh?")
        ),
        listOf(
            Pertanyaan("Apakah Anda pernah memiliki alergi kulit?"),
            Pertanyaan("Apakah Anda sering menggunakan produk kulit tertentu?"),
            Pertanyaan("Apakah Anda memiliki riwayat penyakit kulit dalam keluarga?")
        )
    )


    private var currentSectionIndex = 0

    private val allAnswers = HashMap<String, Boolean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening)

        recyclerView = findViewById(R.id.recyclerview)
        nextButton = findViewById(R.id.imageButton)
        sectionTitle = findViewById(R.id.textView2)

        recyclerView.layoutManager = LinearLayoutManager(this)

        loadSection(currentSectionIndex)

        nextButton.setOnClickListener {
            if (currentSectionIndex < sections.size - 1) {
                currentSectionIndex++
                loadSection(currentSectionIndex)
            } else {
                val intent = Intent(this, citra_medis::class.java) // Ganti ke aktivitas selanjutnya
                startActivity(intent)
                finish()
            }
        }
    }

    private fun loadSection(index: Int) {
        sectionTitle.text = sections[index]
        val adapter = PertanyaanAdapter(questionsPerSection[index].toMutableList()) { questionText, answer ->
            allAnswers[questionText] = answer
        }
        recyclerView.adapter = adapter
    }
}
