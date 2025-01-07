package com.example.skiva.UI

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.adapter.NavigationController
import com.example.skiva.model.SkincarePromo
import com.example.skiva.model.TreathmentPromo

class promo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo)

        // Data dummy untuk skincare
        val skincareData = listOf(
            SkincarePromo("Toner AJY", "Toner ini itu", R.drawable.gambar_penyakit, "700K", "Rp. 200.000"),
            SkincarePromo("Serum XYZ", "Serum untuk kulit cerah", R.drawable.gambar_penyakit, "500K", "Rp. 150.000")
        )
        val skincareRecyclerView = findViewById<RecyclerView>(R.id.recycler_view_skincare)
        skincareRecyclerView.layoutManager = LinearLayoutManager(this)
        skincareRecyclerView.adapter = AdapterSkincarePromo(skincareData)

        // Data dummy untuk treathment
        val treathmentData = listOf(
            TreathmentPromo("Acne Laser", "Laser ini itu", R.drawable.gambar_penyakit, "1000K", "Rp. 500.000"),
            TreathmentPromo("Hair Removal", "Treatment hair removal", R.drawable.gambar_penyakit, "1200K", "Rp. 600.000")
        )
        val treathmentRecyclerView = findViewById<RecyclerView>(R.id.recycler_view_treathment)
        treathmentRecyclerView.layoutManager = LinearLayoutManager(this)
        treathmentRecyclerView.adapter = AdapterTreathmentPromo(treathmentData)

        val navigationView = findViewById<View>(R.id.header_shortcut_include)
        NavigationController(this, navigationView)
    }
}
