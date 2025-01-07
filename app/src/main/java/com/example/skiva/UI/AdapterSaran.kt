package com.example.skiva.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skiva.R
import com.example.skiva.model.Saran

class AdapterSaran(private val data: List<Saran>) :
    RecyclerView.Adapter<AdapterSaran.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaObat: TextView = itemView.findViewById(R.id.namaObat)
        val catatan: TextView = itemView.findViewById(R.id.textView27)
        val linkProduk: ImageButton = itemView.findViewById(R.id.LinkProduk)
        val gambarSaran: ImageView = itemView.findViewById(R.id.imgSaran)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.saran, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        // Set data ke view
        holder.namaObat.text = item.namaObat
        holder.catatan.text = item.catatan

        // Load gambar produk menggunakan Glide (untuk URL atau drawable lokal)
        Glide.with(holder.itemView.context)
            .load(item.gambarSaran) // URL atau nama file drawable
            .placeholder(R.drawable.placeholder_image) // Placeholder gambar
            .into(holder.gambarSaran)

        // Click listener untuk link produk
        holder.linkProduk.setOnClickListener {
            // Aksi ketika tombol link diklik
            // Misal, buka URL di browser
        }
    }

    override fun getItemCount() = data.size
}
