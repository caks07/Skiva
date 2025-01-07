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
            Pertanyaan(id = "1", text = "Apakah Anda merasa demam?"),
            Pertanyaan(id = "2", text = "Apakah Anda merasa batuk kering?"),
            Pertanyaan(id = "3", text = "Apakah Anda merasa sesak napas?")
        ),
        listOf(
            Pertanyaan(id = "4", text = "Apakah Anda merasa gatal-gatal?"),
            Pertanyaan(id = "5", text = "Apakah Anda memiliki ruam pada kulit?"),
            Pertanyaan(id = "6", text = "Apakah ada luka yang tidak sembuh?")
        ),
        listOf(
            Pertanyaan(id = "7", text = "Apakah Anda pernah memiliki alergi kulit?"),
            Pertanyaan(id = "8", text = "Apakah Anda sering menggunakan produk kulit tertentu?"),
            Pertanyaan(id = "9", text = "Apakah Anda memiliki riwayat penyakit kulit dalam keluarga?")
        )
    )

    private var currentSectionIndex = 0
    private val allAnswers = mutableMapOf<String, Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening)

        recyclerView = findViewById(R.id.recyclerview)
        nextButton = findViewById(R.id.imageButton)
        sectionTitle = findViewById(R.id.textView2)

        recyclerView.layoutManager = LinearLayoutManager(this)
        loadSection(currentSectionIndex)

        nextButton.setOnClickListener {
            if (!areAllQuestionsAnswered()) {
                // Show a message if not all questions are answered
                return@setOnClickListener
            }

            if (currentSectionIndex < sections.size - 1) {
                currentSectionIndex++
                loadSection(currentSectionIndex)
            } else {
                val intent = Intent(this, citra_medis::class.java)
                intent.putExtra("answers", HashMap(allAnswers))
                startActivity(intent)
                finish()
            }
        }
    }

    private fun areAllQuestionsAnswered(): Boolean {
        val unansweredQuestions = questionsPerSection[currentSectionIndex].filter { !allAnswers.containsKey(it.id) }
        if (unansweredQuestions.isNotEmpty()) {
            // Inform user to answer all questions
            return false
        }
        return true
    }

    private fun loadSection(index: Int) {
        sectionTitle.text = sections[index]
        val adapter = AdapterPertanyaan(questionsPerSection[index]) { pertanyaan, isSelected ->
            allAnswers[pertanyaan.id] = isSelected
        }
        recyclerView.adapter = adapter
    }

}
