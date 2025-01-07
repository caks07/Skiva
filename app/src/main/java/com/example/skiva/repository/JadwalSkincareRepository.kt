package com.example.skiva.repository

import com.example.skiva.model.JadwalSkincare
import com.google.firebase.database.*

class JadwalSkincareRepository {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun getJadwalSkincareByTime(
        userId: String,
        waktu: String,
        onSuccess: (List<JadwalSkincare>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val reference = database.child("users/$userId/skincare")
        reference.orderByChild("waktu").equalTo(waktu).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val result = snapshot.children.mapNotNull { it.getValue(JadwalSkincare::class.java) }
                onSuccess(result)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(Exception(error.message))
            }
        })
    }

}
