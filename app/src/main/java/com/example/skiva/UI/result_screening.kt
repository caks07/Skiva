package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.skiva.R
import com.example.skiva.api.ApiClientScreening
import com.example.skiva.model.AnalyzeRequest
import com.example.skiva.model.AnalyzeResponse
import com.example.skiva.model.ResultScreening
import com.example.skiva.viewModel.ScreeningViewModel
import com.google.firebase.auth.FirebaseAuth
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
    private lateinit var viewModel: ScreeningViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screening)

        viewModel = ViewModelProvider(this)[ScreeningViewModel::class.java]

        progressBar = findViewById(R.id.progressBar)
        percentageText = findViewById(R.id.PersenHasilScreening)
        diseaseText = findViewById(R.id.NamaPenyakit)
        recommendationText = findViewById(R.id.penjelasanRekomendasi)
        saveButton = findViewById(R.id.simpan)

        val imageBase64 = intent.getStringExtra("imageBase64") ?: ""
        val notes = intent.getStringExtra("notes") ?: ""
        val symptoms = intent.getStringArrayListExtra("symptoms") ?: arrayListOf()

        Log.d("ResultScreening", "Starting analysis with symptoms: $symptoms and notes: $notes")
        if (imageBase64.isNotBlank()) {
            analyzeDataWithImage(imageBase64, symptoms, notes)
        } else {
            analyzeDataWithoutImage(symptoms, notes)
        }

        saveButton.setOnClickListener {
            resultData?.let {
                Log.d("ResultScreening", "Saving result to Firebase: $it")
                saveToFirebase(it)
                navigateToHome()
            } ?: run {
                Log.w("ResultScreening", "No result data to save!")
            }
        }
    }

    private fun analyzeDataWithImage(imageBase64: String, symptoms: List<String>, notes: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = AnalyzeRequest(imageBase64, symptoms, notes)
                Log.d("ResultScreening", "Sending request to API with image: $request")
                val response = ApiClientScreening.instance.analyzeImage(request).execute()
                handleApiResponse(response)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("ResultScreening", "Exception during API call", e)
                    showError("Gagal menganalisis gambar.")
                }
            }
        }
    }

    private fun analyzeDataWithoutImage(symptoms: List<String>, notes: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = AnalyzeRequest(imageBase64 = "", symptoms = symptoms, notes = notes)
                Log.d("ResultScreening", "Sending request to API without image: $request")
                val response = ApiClientScreening.instance.analyzeImage(request).execute()
                handleApiResponse(response)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("ResultScreening", "Exception during API call", e)
                    showError("Gagal menganalisis data.")
                }
            }
        }
    }

    private suspend fun handleApiResponse(response: retrofit2.Response<AnalyzeResponse>) {
        withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                resultData = response.body()
                if (resultData != null) {
                    Log.d("ResultScreening", "API response received: $resultData")
                    displayResults(resultData!!)
                } else {
                    Log.w("ResultScreening", "API response is empty.")
                    showError("Respons kosong dari server.")
                }
            } else {
                val error = response.errorBody()?.string()
                Log.e("ResultScreening", "API error: $error")
                showError("Error: $error")
            }
        }
    }

    private fun displayResults(result: AnalyzeResponse) {
        Log.d("ResultScreening", "Displaying results: $result")
        progressBar.progress = result.percentage
        percentageText.text = "${result.percentage}%"
        diseaseText.text = result.disease
        recommendationText.text = result.recommendation

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: "Unknown User"
        val currentTime = System.currentTimeMillis().toString()
        val resultScreening = ResultScreening(
            userId = userId,
            namaPenyakit = result.disease,
            persentaseKemungkinan = result.percentage,
            rekomendasi = result.recommendation,
            tanggal = currentTime
        )

        viewModel.addResult(resultScreening)
    }

    private fun saveToFirebase(result: AnalyzeResponse) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val databaseReference = FirebaseDatabase.getInstance().getReference("users/$userId/history")

        val data = mapOf(
            "disease" to result.disease,
            "percentage" to result.percentage,
            "recommendation" to result.recommendation,
            "timestamp" to System.currentTimeMillis()
        )

        databaseReference.push().setValue(data)
            .addOnSuccessListener {
                Log.d("ResultScreening", "Data saved successfully: $data")
                Toast.makeText(this, "Hasil disimpan", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.e("ResultScreening", "Failed to save data", it)
                Toast.makeText(this, "Gagal menyimpan hasil", Toast.LENGTH_SHORT).show()
            }
    }

    private fun navigateToHome() {
        Log.d("ResultScreening", "Navigating to home page.")
        val intent = Intent(this, home_page::class.java)
        startActivity(intent)
        finish()
    }

    private fun showError(message: String) {
        Log.e("ResultScreening", message)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
