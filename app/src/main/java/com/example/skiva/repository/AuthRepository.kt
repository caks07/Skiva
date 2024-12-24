package com.example.skiva.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class AuthRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    // Login dengan email dan password
    fun loginWithEmail(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onFailure(e.message ?: "Login failed") }
    }

    // Login dengan Google Credential
    fun loginWithGoogle(idToken: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onFailure(e.message ?: "Google Sign-In failed") }
    }
    fun getCurrentUserId(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid
    }

}
