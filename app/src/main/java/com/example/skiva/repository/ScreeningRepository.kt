package com.example.skiva.repository

import com.example.skiva.model.ResultScreening
import com.google.firebase.database.FirebaseDatabase

class ScreeningRepository {
    private val databaseReference = FirebaseDatabase.getInstance()
        .getReference("users")

    fun saveResultScreening(
        userId: String,
        result: ResultScreening,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val id = databaseReference.child(userId).child("screenings").push().key ?: ""
        val resultWithId = result.copy(id = id)

        databaseReference.child(userId).child("screenings").child(id).setValue(resultWithId)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it.message ?: "Unknown error") }
    }
}
