package com.example.skiva.repository

import com.example.skiva.model.DataObat
import com.example.skiva.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UserRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase = FirebaseDatabase.getInstance("https://skiva-pab-default-rtdb.asia-southeast1.firebasedatabase.app")
    private val usersRef = firebaseDatabase.getReference("users")

    // Registrasi User dengan Firebase Authentication
    fun registerUser(
        user: User,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { authResult ->
                // Simpan data user ke Realtime Database
                val uid = authResult.user?.uid ?: return@addOnSuccessListener
                usersRef.child(uid).setValue(user)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { e -> onFailure(e.message ?: "Failed to save user data") }
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Registration failed")
            }
    }
    fun saveObat(
        userId: String,
        obatList: List<DataObat>,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val obatRef = firebaseDatabase.getReference("users").child(userId).child("obat")
        obatRef.setValue(obatList)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onFailure(e.message ?: "Failed to save obat data") }
    }


}
