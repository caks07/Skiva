package com.example.skiva.repository

import android.util.Log
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
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    Log.d("JadwalObatRepository", "Snapshot is empty")
                    onSuccess(emptyList())
                    return
                }

                val result = mutableListOf<JadwalObat>()
                for (data in snapshot.children) {
                    val obat = data.getValue(JadwalObat::class.java)
                    Log.d("JadwalObatRepository", "Data obat: $obat")

                    if (obat != null) {
                        val waktuArray = obat.waktu.split(",").map { it.trim() } // Hapus spasi tambahan
                        Log.d("JadwalObatRepository", "waktuArray: $waktuArray, waktu: $waktu")

                        if (waktuArray.any { it.equals(waktu, ignoreCase = true) }) {
                            result.add(obat)
                        }
                    }
                }
                Log.d("JadwalObatRepository", "Data untuk $waktu: $result")
                onSuccess(result)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("JadwalObatRepository", "Error: ${error.message}")
                onFailure(Exception(error.message))
            }
        })
    }
}

