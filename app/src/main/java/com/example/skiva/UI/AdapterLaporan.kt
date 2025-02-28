package com.example.skiva.UI

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.LaporanData

class AdapterLaporan(private val data: List<LaporanData>) :
    RecyclerView.Adapter<AdapterLaporan.LaporanViewHolder>() {

    class LaporanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaDokter: TextView = itemView.findViewById(R.id.Email)
        val tanggal: TextView = itemView.findViewById(R.id.textView6)
        val button: Button = itemView.findViewById(R.id.button12)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaporanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_laporan, parent, false)
        return LaporanViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaporanViewHolder, position: Int) {
        val laporan = data[position]
        holder.namaDokter.text = laporan.namaDokter
        holder.tanggal.text = laporan.tanggal
        holder.button.setOnClickListener {
            // Aksi saat tombol "Selengkapnya" ditekan
            val context = holder.itemView.context
            val intent = Intent(context, detail_laporan::class.java).apply {
                putExtra("NAMA_DOKTER", laporan.namaDokter)
                putExtra("TANGGAL", laporan.tanggal)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = data.size
}
