package com.example.skiva.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skiva.model.JadwalSkincare
import com.example.skiva.repository.JadwalSkincareRepository

class JadwalSkincareViewModel(private val repository: JadwalSkincareRepository) : ViewModel() {

    val jadwalPagi = MutableLiveData<List<JadwalSkincare>>()
    val jadwalSiang = MutableLiveData<List<JadwalSkincare>>()
    val jadwalSore = MutableLiveData<List<JadwalSkincare>>()
    val jadwalMalam = MutableLiveData<List<JadwalSkincare>>()
    val errorMessage = MutableLiveData<String>()

    fun loadJadwalSkincare(userId: String) {
        repository.getJadwalSkincareByTime(userId, "Pagi", { data ->
            jadwalPagi.postValue(data)
        }, { error ->
            errorMessage.postValue("Failed to load Jadwal Pagi: ${error.message}")
        })

        repository.getJadwalSkincareByTime(userId, "Siang", { data ->
            jadwalSiang.postValue(data)
        }, { error ->
            errorMessage.postValue("Failed to load Jadwal Siang: ${error.message}")
        })

        repository.getJadwalSkincareByTime(userId, "Sore", { data ->
            jadwalSore.postValue(data)
        }, { error ->
            errorMessage.postValue("Failed to load Jadwal Sore: ${error.message}")
        })

        repository.getJadwalSkincareByTime(userId, "Malam", { data ->
            jadwalMalam.postValue(data)
        }, { error ->
            errorMessage.postValue("Failed to load Jadwal Malam: ${error.message}")
        })
    }
}
