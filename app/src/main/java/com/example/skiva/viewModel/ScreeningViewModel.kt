package com.example.skiva.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skiva.model.AnalyzeResponse
import com.example.skiva.model.ResultScreening

class ScreeningViewModel : ViewModel() {
    private val _screeningResults = MutableLiveData<List<ResultScreening>>()
    val screeningResults: LiveData<List<ResultScreening>> get() = _screeningResults

    private val results = mutableListOf<ResultScreening>()

    fun addResult(result: ResultScreening) {
        results.add(result)
        _screeningResults.value = results
    }
}