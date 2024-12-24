package com.example.skiva.UI

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.Penyakit

class PenyakitAdapter(private val listPenyakit: List<Penyakit>) :
    RecyclerView.Adapter<PenyakitAdapter.PenyakitViewHolder>() {

    inner class PenyakitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagePenyakit: ImageView = itemView.findViewById(R.id.imagePenyakit)
        val namaPenyakit: TextView = itemView.findViewById(R.id.namaPenyakit)
        val deskripsi: TextView = itemView.findViewById(R.id.deskripsi)
        val kategoriPenyakit: TextView = itemView.findViewById(R.id.kategoriPenyakit)
        val buttonPenjelasan: Button = itemView.findViewById(R.id.buttonPenjelasan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenyakitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_penyakit, parent, false)
        return PenyakitViewHolder(view)
    }

    override fun onBindViewHolder(holder: PenyakitViewHolder, position: Int) {
        val penyakit = listPenyakit[position]
        holder.imagePenyakit.setImageResource(penyakit.imageResource)
        holder.namaPenyakit.text = penyakit.nama
        holder.deskripsi.text = penyakit.deskripsi
        holder.kategoriPenyakit.text = penyakit.kategori

        holder.buttonPenjelasan.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, kategori_penyakit::class.java)
            intent.putExtra("EXTRA_NAMA", penyakit.nama)
            intent.putExtra("EXTRA_DESKRIPSI", penyakit.deskripsi)
            intent.putExtra("EXTRA_KATEGORI", penyakit.kategori)
            intent.putExtra("EXTRA_IMAGE", penyakit.imageResource)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listPenyakit.size
}
