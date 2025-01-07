package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.adapter.NavigationController
import com.example.skiva.model.LaporanData

class laporan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan)

        // Data dummy untuk bulan Januari
        val dataJanuari = listOf(
            LaporanData("Dr. Agra Cawiksana", "17 Januari 2024"),
            LaporanData("Dr. Pradipda Ubay", "25 Januari 2024")
        )

        // Data dummy untuk bulan November
        val dataNovember = listOf(
            LaporanData("Dr. Rayan Lakmanaa", "12 November 2024"),
            LaporanData("Dr. Wirawan Hidayat", "28 November 2024")
        )

        // Data dummy untuk bulan Desember
        val dataDesember = listOf(
            LaporanData("Dr. Wingsu Aya P.", "5 Desember 2024"),
            LaporanData("Dr. Ubay Nugroha", "22 Desember 2024")
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

        val buttonBack: ImageButton = findViewById(R.id.imageButtonBack)
        buttonBack.setOnClickListener {
            val intent = Intent(this, home_page::class.java)
            startActivity(intent)
        }

        val buttonNotif: ImageButton = findViewById(R.id.imageButtonNotif)
        buttonNotif.setOnClickListener {
            val intent = Intent(this, notifikasi::class.java)
            startActivity(intent)
        }

        val navigationView = findViewById<View>(R.id.header_shortcut_include)
        NavigationController(this, navigationView)
    }
}
