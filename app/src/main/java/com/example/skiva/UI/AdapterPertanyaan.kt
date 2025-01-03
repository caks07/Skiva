package com.example.skiva.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.Pertanyaan

class AdapterPertanyaan(
    private val pertanyaanList: List<Pertanyaan>,
    private val onAnswerSelected: (Pertanyaan, Boolean) -> Unit
) : RecyclerView.Adapter<AdapterPertanyaan.PertanyaanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PertanyaanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pertanyaan, parent, false)
        return PertanyaanViewHolder(view)
    }

    override fun onBindViewHolder(holder: PertanyaanViewHolder, position: Int) {
        val pertanyaan = pertanyaanList[position]
        holder.bind(pertanyaan)
    }

    override fun getItemCount(): Int = pertanyaanList.size

    inner class PertanyaanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val questionText: TextView = itemView.findViewById(R.id.question_text)
        private val checkBoxYes: CheckBox = itemView.findViewById(R.id.checkbox_yes)
        private val checkBoxNo: CheckBox = itemView.findViewById(R.id.checkbox_no)

        fun bind(pertanyaan: Pertanyaan) {
            questionText.text = pertanyaan.text

            checkBoxYes.setOnCheckedChangeListener(null)
            checkBoxNo.setOnCheckedChangeListener(null)

            checkBoxYes.isChecked = pertanyaan.isYesSelected
            checkBoxNo.isChecked = pertanyaan.isNoSelected

            checkBoxYes.setOnCheckedChangeListener { _, isChecked ->
                pertanyaan.isYesSelected = isChecked
                if (isChecked) {
                    checkBoxNo.isChecked = false
                    pertanyaan.isNoSelected = false
                }
                onAnswerSelected(pertanyaan, isChecked)
            }

            checkBoxNo.setOnCheckedChangeListener { _, isChecked ->
                pertanyaan.isNoSelected = isChecked
                if (isChecked) {
                    checkBoxYes.isChecked = false
                    pertanyaan.isYesSelected = false
                }
                onAnswerSelected(pertanyaan, !isChecked)
            }
        }
    }
}
