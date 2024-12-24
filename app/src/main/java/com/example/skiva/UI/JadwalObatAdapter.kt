package com.example.skiva.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.JadwalObat

class JadwalObatAdapter(private val data: List<JadwalObat>) :
    RecyclerView.Adapter<JadwalObatAdapter.JadwalObatViewHolder>() {

    inner class JadwalObatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaObat: TextView = itemView.findViewById(R.id.namaObat)
        val catatan: TextView = itemView.findViewById(R.id.textView27)
        val dosis: TextView = itemView.findViewById(R.id.dosis)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalObatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.jadwal_obat, parent, false)
        return JadwalObatViewHolder(view)
    }

    override fun onBindViewHolder(holder: JadwalObatViewHolder, position: Int) {
        val item = data[position]
        holder.namaObat.text = item.namaObat
        holder.catatan.text = item.catatan
        holder.dosis.text = item.dosis
        holder.checkBox.isChecked = item.isChecked

        // Log untuk debugging
        println("Binding item: ${item.namaObat}, Catatan: ${item.catatan}, Dosis: ${item.dosis}")

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
        }
    }

    override fun getItemCount(): Int = data.size
}

