package com.example.skiva.model

data class DataObat(
    var namaObat: String = "",
    var tanggalMulai: String = "",
    var tanggalAkhir: String = "",
    var frekuensi: String = "",
    var dosis: String = "",
    var jenis: String = "",
    var waktu: String = "", // Contoh: "Pagi,Siang"
    var catatan: String = "" // Tambahkan properti catatan
)


