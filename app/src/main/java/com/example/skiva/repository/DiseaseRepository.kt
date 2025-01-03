package com.example.skiva.repository

import com.example.skiva.model.Disease
import com.google.firebase.database.*

class DiseaseRepository {
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance("https://skiva-pab-default-rtdb.asia-southeast1.firebasedatabase.app")
            .getReference("diseases")

    fun getDiseasesByCategory(
        category: String,
        onSuccess: (List<Disease>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        databaseReference.orderByChild("kategori").equalTo(category)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val diseases = mutableListOf<Disease>()
                    for (data in snapshot.children) {
                        val disease = data.getValue(Disease::class.java)
                        disease?.let { diseases.add(it) }
                    }
                    onSuccess(diseases)
                }

                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }
            })
    }
}
