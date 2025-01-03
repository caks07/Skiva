package com.example.skiva.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skiva.model.JadwalObat
import com.example.skiva.repository.JadwalObatRepository

class JadwalObatViewModel(private val repository: JadwalObatRepository) : ViewModel() {

    val jadwalObatPagi = MutableLiveData<List<JadwalObat>>()
    val jadwalObatSiang = MutableLiveData<List<JadwalObat>>()
    val jadwalObatSore = MutableLiveData<List<JadwalObat>>()
    val jadwalObatMalam = MutableLiveData<List<JadwalObat>>()

    fun loadJadwalObat(userId: String) {
        repository.getJadwalObat(userId, "Pagi", { data ->
            Log.d("JadwalObatViewModel", "Data Pagi: $data")
            jadwalObatPagi.postValue(data)
        }, { Log.e("JadwalObatViewModel", "Error loading Pagi: ${it.message}") })

        repository.getJadwalObat(userId, "Siang", { data ->
            Log.d("JadwalObatViewModel", "Data Siang: $data")
            jadwalObatSiang.postValue(data)
        }, { Log.e("JadwalObatViewModel", "Error loading Siang: ${it.message}") })

        repository.getJadwalObat(userId, "Sore", { data ->
            Log.d("JadwalObatViewModel", "Data Sore: $data")
            jadwalObatSore.postValue(data)
        }, { Log.e("JadwalObatViewModel", "Error loading Sore: ${it.message}") })

        repository.getJadwalObat(userId, "Malam", { data ->
            Log.d("JadwalObatViewModel", "Data Malam: $data")
            jadwalObatMalam.postValue(data)
        }, { Log.e("JadwalObatViewModel", "Error loading Malam: ${it.message}") })
    }
}



