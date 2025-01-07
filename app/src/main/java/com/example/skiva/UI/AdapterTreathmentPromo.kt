package com.example.skiva.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.TreathmentPromo

class AdapterTreathmentPromo(private val data: List<TreathmentPromo>) :
    RecyclerView.Adapter<AdapterTreathmentPromo.TreathmentViewHolder>() {

    class TreathmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.namaTreathment)
        val deskripsi: TextView = itemView.findViewById(R.id.deskripsiTreathment)
        val gambar: ImageView = itemView.findViewById(R.id.imageTreathment)
        val hargaKoin: Button = itemView.findViewById(R.id.buttonHargaKoin)
        val hargaRupiah: Button = itemView.findViewById(R.id.buttonHargaRupiah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreathmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_promo_treathment, parent, false)
        return TreathmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: TreathmentViewHolder, position: Int) {
        val treathmentPromo = data[position]
        holder.nama.text = treathmentPromo.nama
        holder.deskripsi.text = treathmentPromo.deskripsi
        holder.gambar.setImageResource(treathmentPromo.gambar)
        holder.hargaKoin.text = treathmentPromo.hargaKoin
        holder.hargaRupiah.text = treathmentPromo.hargaRupiah
    }

    override fun getItemCount(): Int = data.size
}
