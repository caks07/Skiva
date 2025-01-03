package com.example.skiva.utils

import com.example.skiva.model.Disease
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DatabaseSeeder {
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance("https://skiva-pab-default-rtdb.asia-southeast1.firebasedatabase.app")
            .getReference("diseases")

    fun seedData(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val diseases = listOf(
            // Kategori Wajah
            Disease(
                id_penyakit = "1",
                nama_penyakit = "Acne",
                kategori = "Wajah",
                deskripsi_penyakit = "Kondisi kulit yang disebabkan oleh penyumbatan pori-pori...",
                penyebab = "Penyumbatan folikel rambut oleh minyak atau sel kulit mati.",
                gejala = "Jerawat, komedo, kulit kemerahan.",
                pencegahan = "Cuci muka teratur.",
                pengobatan_awal = "Gunakan krim antibiotik.",
                gambar_penyakit = "https://example.com/image1.jpg"
            ),
            Disease(
                id_penyakit = "2",
                nama_penyakit = "Milia",
                kategori = "Wajah",
                deskripsi_penyakit = "Kondisi kulit yang menyebabkan benjolan kecil berwarna putih.",
                penyebab = "Penumpukan keratin di bawah kulit.",
                gejala = "Benjolan kecil, putih, tidak menyakitkan.",
                pencegahan = "Hindari produk perawatan kulit berminyak.",
                pengobatan_awal = "Gunakan retinoid topikal.",
                gambar_penyakit = "https://example.com/image2.jpg"
            ),
            Disease(
                id_penyakit = "3",
                nama_penyakit = "Rosacea",
                kategori = "Wajah",
                deskripsi_penyakit = "Kemerahan pada wajah yang kronis.",
                penyebab = "Pelebaran pembuluh darah di bawah kulit.",
                gejala = "Kemerahan, pembuluh darah terlihat, jerawat kecil.",
                pencegahan = "Hindari sinar matahari langsung.",
                pengobatan_awal = "Gunakan antibiotik topikal.",
                gambar_penyakit = "https://example.com/image3.jpg"
            ),

            // Kategori Menular
            Disease(
                id_penyakit = "4",
                nama_penyakit = "Cacar Air",
                kategori = "Menular",
                deskripsi_penyakit = "Infeksi kulit akibat virus Varicella-Zoster.",
                penyebab = "Infeksi virus Varicella-Zoster.",
                gejala = "Lepuh merah, demam, gatal.",
                pencegahan = "Vaksinasi Varicella.",
                pengobatan_awal = "Gunakan losion calamine.",
                gambar_penyakit = "https://example.com/image4.jpg"
            ),
            Disease(
                id_penyakit = "5",
                nama_penyakit = "Kurap",
                kategori = "Menular",
                deskripsi_penyakit = "Infeksi jamur pada kulit berbentuk lingkaran merah.",
                penyebab = "Infeksi jamur dermatofita.",
                gejala = "Lingkaran merah, gatal, bersisik.",
                pencegahan = "Hindari kontak langsung dengan penderita.",
                pengobatan_awal = "Gunakan krim antijamur.",
                gambar_penyakit = "https://example.com/image5.jpg"
            ),
            Disease(
                id_penyakit = "6",
                nama_penyakit = "Herpes Zoster",
                kategori = "Menular",
                deskripsi_penyakit = "Infeksi kulit akibat virus Varicella-Zoster yang aktif kembali.",
                penyebab = "Virus Varicella-Zoster.",
                gejala = "Ruam merah, nyeri, lepuh berisi cairan.",
                pencegahan = "Vaksinasi Zoster.",
                pengobatan_awal = "Gunakan antivirus oral.",
                gambar_penyakit = "https://example.com/image6.jpg"
            ),

            // Kategori Tidak Menular
            Disease(
                id_penyakit = "7",
                nama_penyakit = "Psoriasis",
                kategori = "Tidak Menular",
                deskripsi_penyakit = "Penyakit kulit kronis yang menyebabkan kulit bersisik.",
                penyebab = "Gangguan autoimun.",
                gejala = "Kulit tebal, bersisik, gatal.",
                pencegahan = "Hindari stres berlebihan.",
                pengobatan_awal = "Gunakan krim kortikosteroid.",
                gambar_penyakit = "https://example.com/image7.jpg"
            ),
            Disease(
                id_penyakit = "8",
                nama_penyakit = "Vitiligo",
                kategori = "Tidak Menular",
                deskripsi_penyakit = "Kehilangan warna kulit akibat hilangnya melanin.",
                penyebab = "Gangguan autoimun.",
                gejala = "Bercak putih pada kulit.",
                pencegahan = "Hindari paparan sinar matahari langsung.",
                pengobatan_awal = "Gunakan krim kortikosteroid.",
                gambar_penyakit = "https://example.com/image8.jpg"
            ),
            Disease(
                id_penyakit = "9",
                nama_penyakit = "Eksim",
                kategori = "Tidak Menular",
                deskripsi_penyakit = "Peradangan kulit kronis.",
                penyebab = "Alergi atau iritasi.",
                gejala = "Kulit gatal, pecah-pecah, merah.",
                pencegahan = "Hindari pemicu alergi.",
                pengobatan_awal = "Gunakan pelembap dan obat antiradang.",
                gambar_penyakit = "https://example.com/image9.jpg"
            ),

            // Kategori Lainnya
            Disease(
                id_penyakit = "10",
                nama_penyakit = "Melasma",
                kategori = "Lainnya",
                deskripsi_penyakit = "Bercak coklat pada kulit akibat paparan sinar matahari.",
                penyebab = "Paparan sinar UV.",
                gejala = "Bercak coklat, tidak gatal.",
                pencegahan = "Gunakan tabir surya.",
                pengobatan_awal = "Gunakan krim pemutih.",
                gambar_penyakit = "https://example.com/image10.jpg"
            ),
            Disease(
                id_penyakit = "11",
                nama_penyakit = "Dermatitis Kontak",
                kategori = "Lainnya",
                deskripsi_penyakit = "Reaksi alergi akibat kontak dengan zat tertentu.",
                penyebab = "Alergi terhadap zat kimia atau logam.",
                gejala = "Kulit merah, gatal, bengkak.",
                pencegahan = "Hindari pemicu alergi.",
                pengobatan_awal = "Gunakan antihistamin.",
                gambar_penyakit = "https://example.com/image11.jpg"
            ),
            Disease(
                id_penyakit = "12",
                nama_penyakit = "Urtikaria",
                kategori = "Lainnya",
                deskripsi_penyakit = "Ruam kulit akibat alergi.",
                penyebab = "Reaksi alergi.",
                gejala = "Ruam merah, gatal.",
                pencegahan = "Hindari pemicu alergi.",
                pengobatan_awal = "Gunakan antihistamin.",
                gambar_penyakit = "https://example.com/image12.jpg"
            )
        )

        for (disease in diseases) {
            databaseReference.child(disease.id_penyakit).setValue(disease)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        onFailure(task.exception?.message ?: "Unknown error occurred")
                    }
                }
        }
    }
}
