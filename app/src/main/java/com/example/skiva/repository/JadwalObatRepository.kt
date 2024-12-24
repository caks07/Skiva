package com.example.skiva.repository

import com.example.skiva.model.JadwalObat
import com.google.firebase.database.*

class JadwalObatRepository {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference.child("obat")

    fun getJadwalObat(
        userId: String,
        waktu: String,
        onSuccess: (List<JadwalObat>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val reference = FirebaseDatabase.getInstance().getReference("users/$userId/obat")
        reference.orderByChild("waktu").equalTo(waktu).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val result = mutableListOf<JadwalObat>()
                for (data in snapshot.children) {
                    val obat = data.getValue(JadwalObat::class.java)
                    if (obat != null) result.add(obat)
                }
                onSuccess(result)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(Exception(error.message))
            }
        })
    }
}
