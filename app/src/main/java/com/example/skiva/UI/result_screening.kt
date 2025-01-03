package com.example.skiva.UI

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skiva.R
import com.example.skiva.api.ApiClientScreening
import com.example.skiva.api.AnalyzeRequest
import com.example.skiva.api.AnalyzeResponse
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class result_screening : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var percentageText: TextView
    private lateinit var diseaseText: TextView
    private lateinit var recommendationText: TextView
    private lateinit var saveButton: ImageButton

    private var resultData: AnalyzeResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screening)

        progressBar = findViewById(R.id.progressBar)
        percentageText = findViewById(R.id.PersenHasilScreening)
        diseaseText = findViewById(R.id.NamaPenyakit)
        recommendationText = findViewById(R.id.penjelasanRekomendasi)
        saveButton = findViewById(R.id.simpan)

        val imageBase64 = intent.getStringExtra("imageBase64") ?: ""
        val notes = intent.getStringExtra("notes") ?: ""
        val symptoms = intent.getStringArrayListExtra("symptoms") ?: arrayListOf()

        analyzeData(imageBase64, symptoms, notes)

        saveButton.setOnClickListener {
            resultData?.let { saveToFirebase(it) }
        }
    }

    private fun analyzeData(imageBase64: String, symptoms: List<String>, notes: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = AnalyzeRequest(imageBase64, symptoms, notes)
                val response = ApiClientScreening.instance.analyzeImage(request).execute()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        resultData = response.body()
                        if (resultData != null) {
                            displayResults(resultData!!)
                        } else {
                            showError("Response is empty.")
                        }
                    } else {
                        val error = response.errorBody()?.string()
                        showError("Error: $error")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showError("Exception: ${e.message}")
                }
            }
        }
    }

    private fun displayResults(result: AnalyzeResponse) {
        progressBar.progress = result.percentage
        percentageText.text = "${result.percentage}%"
        diseaseText.text = result.disease
        recommendationText.text = result.recommendation
    }

    private fun saveToFirebase(result: AnalyzeResponse) {
        val userId = "USER_ID" // Replace with the actual user ID retrieval mechanism
        val databaseReference = FirebaseDatabase.getInstance().getReference("users/$userId/history")

        val data = mapOf(
            "disease" to result.disease,
            "percentage" to result.percentage,
            "recommendation" to result.recommendation,
            "timestamp" to System.currentTimeMillis()
        )

        databaseReference.push().setValue(data)
            .addOnSuccessListener { Toast.makeText(this, "Hasil disimpan", Toast.LENGTH_SHORT).show() }
            .addOnFailureListener { Toast.makeText(this, "Gagal menyimpan hasil", Toast.LENGTH_SHORT).show() }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
