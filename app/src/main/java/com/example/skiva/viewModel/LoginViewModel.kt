package com.example.skiva.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skiva.repository.AuthRepository

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean> get() = _loginStatus

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun loginWithEmail(email: String, password: String) {
        authRepository.loginWithEmail(email, password,
            onSuccess = { _loginStatus.value = true },
            onFailure = { error -> _errorMessage.value = error }
        )
    }

    fun loginWithGoogle(idToken: String) {
        authRepository.loginWithGoogle(idToken,
            onSuccess = { _loginStatus.value = true },
            onFailure = { error -> _errorMessage.value = error }
        )
    }

    fun getUserId(): String? {
        return authRepository.getCurrentUserId()
    }

}
