package com.example.skiva.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skiva.repository.JadwalObatRepository

class JadwalObatViewModelFactory(private val repository: JadwalObatRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JadwalObatViewModel::class.java)) {
            return JadwalObatViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
