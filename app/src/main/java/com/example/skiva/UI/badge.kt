package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.DataBadge

class badge : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badge)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = BadgeAdapter(mutableListOf())
        recyclerView.adapter = adapter

        adapter.addLaporan(DataBadge("Ini adalah pencapaian karena kamu telah menjalankan treathment selama 7 hari berturut turut", "Pencapaian 7 Hari Quest", "700 poin"))
        adapter.addLaporan(DataBadge("Badge ini didapatkan hanya untuk orang keren sepertimu, teruslah patuhi jadwal obatmu ya", "Capaian Jadwal Obat", "500 poin"))
        // Navigasi ke halaman badge
        val buttonBack: ImageButton = findViewById(R.id.back)
        buttonBack.setOnClickListener {
            val intent = Intent(this, home_page::class.java)
            startActivity(intent)
        }
    }
}
