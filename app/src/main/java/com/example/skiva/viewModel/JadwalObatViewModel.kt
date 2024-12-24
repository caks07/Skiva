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
            jadwalObatPagi.postValue(data)
            Log.d("JadwalObatViewModel", "Pagi: $data")
        }, { Log.e("JadwalObatViewModel", "Error loading Pagi") })

        repository.getJadwalObat(userId, "Siang", { data ->
            jadwalObatSiang.postValue(data)
            Log.d("JadwalObatViewModel", "Siang: $data")
        }, { Log.e("JadwalObatViewModel", "Error loading Siang") })

        repository.getJadwalObat(userId, "Sore", { data ->
            jadwalObatSore.postValue(data)
            Log.d("JadwalObatViewModel", "Sore: $data")
        }, { Log.e("JadwalObatViewModel", "Error loading Sore") })

        repository.getJadwalObat(userId, "Malam", { data ->
            jadwalObatMalam.postValue(data)
            Log.d("JadwalObatViewModel", "Malam: $data")
        }, { Log.e("JadwalObatViewModel", "Error loading Malam") })
    }


}



