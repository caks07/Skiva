package com.example.skiva.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R

class laporan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan)

        // Data dummy untuk bulan Januari
        val dataJanuari = listOf(
            LaporanData("Dr. Agra Cawiksana", "17 Januari 2024"),
            LaporanData("Dr. Rina Permata", "25 Januari 2024")
        )

        // Data dummy untuk bulan November
        val dataNovember = listOf(
            LaporanData("Dr. Agra Cawiksana", "12 November 2024"),
            LaporanData("Dr. Siti Hawa", "28 November 2024")
        )

        // Data dummy untuk bulan Desember
        val dataDesember = listOf(
            LaporanData("Dr. Agra Cawiksana", "5 Desember 2024"),
            LaporanData("Dr. Hendra Kusuma", "22 Desember 2024")
        )

        // RecyclerView Januari
        val recyclerViewJanuari = findViewById<RecyclerView>(R.id.recycler_view_januari)
        recyclerViewJanuari.layoutManager = LinearLayoutManager(this)
        recyclerViewJanuari.adapter = AdapterLaporan(dataJanuari)

        // RecyclerView November
        val recyclerViewNovember = findViewById<RecyclerView>(R.id.recycler_view_november)
        recyclerViewNovember.layoutManager = LinearLayoutManager(this)
        recyclerViewNovember.adapter = AdapterLaporan(dataNovember)

        // RecyclerView Desember
        val recyclerViewDesember = findViewById<RecyclerView>(R.id.recycler_view_desember)
        recyclerViewDesember.layoutManager = LinearLayoutManager(this)
        recyclerViewDesember.adapter = AdapterLaporan(dataDesember)
    }
}
