package com.example.skiva.UI

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.Dokter

class AdapterDokter(
    private val context: Context,
    private val dokterList: List<Dokter>
) : RecyclerView.Adapter<AdapterDokter.DokterViewHolder>() {

    inner class DokterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaDokter: TextView = itemView.findViewById(R.id.namaDokter)
        val spesialisasi: TextView = itemView.findViewById(R.id.spesialisasi)
        val buttonSelengkapnya: Button = itemView.findViewById(R.id.button12)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DokterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dokter, parent, false)
        return DokterViewHolder(view)
    }

    override fun onBindViewHolder(holder: DokterViewHolder, position: Int) {
        val dokter = dokterList[position]
        holder.namaDokter.text = dokter.nama
        holder.spesialisasi.text = dokter.spesialisasi

        // Set click listener for the button
        holder.buttonSelengkapnya.setOnClickListener {
            val intent = Intent(context, detail_dokter::class.java).apply {
                putExtra("namaDokter", dokter.nama)
                putExtra("spesialisasi", dokter.spesialisasi)
                putExtra("deskripsi", dokter.deskripsi)
                putExtra("experience", dokter.experience)
                putExtra("photoUrl", dokter.photoUrl)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = dokterList.size
}
