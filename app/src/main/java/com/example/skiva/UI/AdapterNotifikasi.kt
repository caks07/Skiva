package com.example.skiva.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R

class AdapterNotifikasi(private val list: List<Notifikasi>) :
    RecyclerView.Adapter<AdapterNotifikasi.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.textView10)
        val description: TextView = view.findViewById(R.id.textView11)
        val time: TextView = view.findViewById(R.id.jam)
        val icon: ImageView = view.findViewById(R.id.imageView4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notifikasi, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.title
        holder.description.text = item.description
        holder.time.text = item.time
        holder.icon.setImageResource(R.drawable.img_notif) // Sesuaikan dengan ikon yang tersedia
    }

    override fun getItemCount() = list.size
}
