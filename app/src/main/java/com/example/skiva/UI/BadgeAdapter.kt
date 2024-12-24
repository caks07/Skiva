package com.example.skiva.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.DataBadge

class BadgeAdapter(private var laporanList: MutableList<DataBadge>) : RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder>() {

    class BadgeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val detailbadgeTextView: TextView = itemView.findViewById(R.id.detail_badge)
        val judulbadgeTextView: TextView = itemView.findViewById(R.id.judul_badge)
        val poinTextView: TextView = itemView.findViewById(R.id.poin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_badge, parent, false)
        return BadgeViewHolder(view)
    }

    override fun onBindViewHolder(holder: BadgeViewHolder, position: Int) {
        val badge = laporanList[position]
        holder.detailbadgeTextView.text = badge.reccomend
        holder.judulbadgeTextView.text = badge.badgeName
        holder.poinTextView.text = badge.poinn
    }

    override fun getItemCount(): Int {
        return laporanList.size
    }

    fun addLaporan(badge: DataBadge) {
        laporanList.add(badge)
        notifyItemInserted(laporanList.size - 1)
    }
}

