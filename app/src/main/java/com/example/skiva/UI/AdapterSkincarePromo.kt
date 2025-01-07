package com.example.skiva.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.SkincarePromo

class AdapterSkincarePromo(private val data: List<SkincarePromo>) :
    RecyclerView.Adapter<AdapterSkincarePromo.SkincareViewHolder>() {

    class SkincareViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.namaSkincare)
        val deskripsi: TextView = itemView.findViewById(R.id.deskripsiSkincare)
        val gambar: ImageView = itemView.findViewById(R.id.imageSkincare)
        val hargaKoin: Button = itemView.findViewById(R.id.buttonHargaKoin)
        val hargaRupiah: Button = itemView.findViewById(R.id.buttonHargaRupiah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkincareViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_promo_skincare, parent, false)
        return SkincareViewHolder(view)
    }

    override fun onBindViewHolder(holder: SkincareViewHolder, position: Int) {
        val skincarePromo = data[position]
        holder.nama.text = skincarePromo.nama
        holder.deskripsi.text = skincarePromo.deskripsi
        holder.gambar.setImageResource(skincarePromo.gambar)
        holder.hargaKoin.text = skincarePromo.hargaKoin
        holder.hargaRupiah.text = skincarePromo.hargaRupiah
    }

    override fun getItemCount(): Int = data.size
}
