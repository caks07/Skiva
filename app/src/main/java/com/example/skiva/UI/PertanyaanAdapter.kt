package com.example.skiva.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.Pertanyaan

class PertanyaanAdapter(
    private val pertanyaans: MutableList<Pertanyaan>,
    private val onAnswerSelected: (String, Boolean) -> Unit
) : RecyclerView.Adapter<PertanyaanAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionText: TextView = itemView.findViewById(R.id.question_text)
        val checkboxYes: CheckBox = itemView.findViewById(R.id.checkbox_yes)
        val checkboxNo: CheckBox = itemView.findViewById(R.id.checkbox_no)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pertanyaan, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = pertanyaans[position]
        holder.questionText.text = question.text
        holder.checkboxYes.isChecked = question.isYesSelected
        holder.checkboxNo.isChecked = question.isNoSelected

        holder.checkboxYes.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                question.isYesSelected = true
                question.isNoSelected = false
                onAnswerSelected(question.text, true)
                notifyItemChanged(position)
            }
        }

        holder.checkboxNo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                question.isNoSelected = true
                question.isYesSelected = false
                onAnswerSelected(question.text, false)
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = pertanyaans.size
}
