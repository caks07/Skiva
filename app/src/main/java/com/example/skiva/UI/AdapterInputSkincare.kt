package com.example.skiva.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.DataSkincare

class AdapterInputSkincare(private val dataList: MutableList<DataSkincare>) :
    RecyclerView.Adapter<AdapterInputSkincare.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val treatment: EditText = itemView.findViewById(R.id.inputTrathmentPagi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_input_skincare, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.treatment.setText(item.treatment)
        holder.treatment.addTextChangedListener {
            item.treatment = it.toString()
        }
    }

    override fun getItemCount() = dataList.size
}
