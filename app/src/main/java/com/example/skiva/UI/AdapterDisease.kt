package com.example.skiva.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skiva.R
import com.example.skiva.model.Disease

class AdapterDisease(
    private val diseases: List<Disease>,
    private val onClick: (Disease) -> Unit
) : RecyclerView.Adapter<AdapterDisease.DiseaseViewHolder>() {

    class DiseaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.namaPenyakit)
        val category: TextView = view.findViewById(R.id.kategoriPenyakit)
        val symptoms: TextView = view.findViewById(R.id.gejala)
        val image: ImageView = view.findViewById(R.id.imagePenyakit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_penyakit, parent, false)
        return DiseaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {
        val disease = diseases[position]
        holder.name.text = disease.nama_penyakit
        holder.category.text = disease.kategori
        holder.symptoms.text = disease.gejala
        Glide.with(holder.image.context)
            .load(disease.gambar_penyakit)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.image)

        holder.itemView.findViewById<Button>(R.id.buttonPenjelasan).setOnClickListener {
            onClick(disease)
        }
    }

    override fun getItemCount(): Int = diseases.size
}
