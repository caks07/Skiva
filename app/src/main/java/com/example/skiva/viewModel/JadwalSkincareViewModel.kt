package com.example.skiva.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skiva.model.JadwalSkincare
import com.example.skiva.repository.JadwalSkincareRepository

class JadwalSkincareViewModel(private val repository: JadwalSkincareRepository) : ViewModel() {

    val jadwalPagi = MutableLiveData<List<JadwalSkincare>>()
    val jadwalSiang = MutableLiveData<List<JadwalSkincare>>()
    val jadwalSore = MutableLiveData<List<JadwalSkincare>>()
    val jadwalMalam = MutableLiveData<List<JadwalSkincare>>()

    fun loadJadwalSkincare(userId: String) {
        repository.getJadwalSkincare(userId, "Pagi", { data ->
            jadwalPagi.postValue(data)
        }, { /* handle error */ })

        repository.getJadwalSkincare(userId, "Siang", { data ->
            jadwalSiang.postValue(data)
        }, { /* handle error */ })

        repository.getJadwalSkincare(userId, "Sore", { data ->
            jadwalSore.postValue(data)
        }, { /* handle error */ })

        repository.getJadwalSkincare(userId, "Malam", { data ->
            jadwalMalam.postValue(data)
        }, { /* handle error */ })
    }
}
