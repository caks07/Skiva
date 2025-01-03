package com.example.skiva.repository

import com.example.skiva.model.JadwalSkincare
import com.google.firebase.database.*

class JadwalSkincareRepository {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun getJadwalSkincare(
        userId: String,
        waktu: String,
        onSuccess: (List<JadwalSkincare>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val reference = database.child("users/$userId/skincare")
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val result = snapshot.children.mapNotNull { it.getValue(JadwalSkincare::class.java) }
                    .filter { it.waktu.equals(waktu, ignoreCase = true) }
                onSuccess(result)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(Exception(error.message))
            }
        })
    }
}
