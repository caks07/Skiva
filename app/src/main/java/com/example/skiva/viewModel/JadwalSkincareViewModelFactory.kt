package com.example.skiva.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skiva.repository.JadwalSkincareRepository

class JadwalSkincareViewModelFactory(private val repository: JadwalSkincareRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JadwalSkincareViewModel::class.java)) {
            return JadwalSkincareViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
