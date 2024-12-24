package com.example.skiva.UI

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.DataObat
import java.util.Calendar
import android.widget.AdapterView
import android.widget.ArrayAdapter


class InputObatAdapter(private val dataList: MutableList<DataObat>) :
    RecyclerView.Adapter<InputObatAdapter.ViewHolderClass>() {

    inner class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaObat: EditText = itemView.findViewById(R.id.namaObat)
        val tanggalMulai: EditText = itemView.findViewById(R.id.tanggalMulai)
        val tanggalAkhir: EditText = itemView.findViewById(R.id.tanggalAkhir)
        val frekuensi: EditText = itemView.findViewById(R.id.frekuensi)
        val dosis: EditText = itemView.findViewById(R.id.dosis)
        val jenis: Spinner = itemView.findViewById(R.id.jenisObatSpinner)
        val catatan: EditText = itemView.findViewById(R.id.catatan)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.resep_obat, parent, false)
        return ViewHolderClass(view)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val data = dataList[position]

        // Set nilai awal ke setiap field
        holder.namaObat.setText(data.namaObat)
        holder.tanggalMulai.setText(data.tanggalMulai)
        holder.tanggalAkhir.setText(data.tanggalAkhir)
        holder.frekuensi.setText(data.frekuensi)
        holder.dosis.setText(data.dosis)
        holder.catatan.addTextChangedListener { data.catatan = it.toString() }



        // Set adapter untuk spinner jika belum diatur
        if (holder.jenis.adapter == null) {
            val context = holder.jenis.context
            val adapter = ArrayAdapter.createFromResource(
                context,
                R.array.jenis_obat_array, // Pastikan ini sesuai dengan array di strings.xml
                android.R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            holder.jenis.adapter = adapter
        }

        // Set pilihan awal pada spinner
        val jenisPosition = holder.jenis.adapter?.let { adapter ->
            (0 until adapter.count).find { adapter.getItem(it) == data.jenis }
        } ?: 0
        holder.jenis.setSelection(jenisPosition)

        // Listener untuk spinner
        holder.jenis.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                data.jenis = holder.jenis.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Tidak ada tindakan jika tidak ada pilihan
            }
        }

        // Listener untuk EditText
        holder.namaObat.addTextChangedListener { data.namaObat = it.toString() }
        holder.tanggalMulai.setOnClickListener {
            showDatePicker(holder.tanggalMulai) { date -> data.tanggalMulai = date }
        }
        holder.tanggalAkhir.setOnClickListener {
            showDatePicker(holder.tanggalAkhir) { date -> data.tanggalAkhir = date }
        }
        holder.frekuensi.addTextChangedListener { data.frekuensi = it.toString() }
        holder.dosis.addTextChangedListener { data.dosis = it.toString() }
    }

    override fun getItemCount(): Int = dataList.size

    private fun showDatePicker(editText: EditText, onDateSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            editText.context,
            { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth-${month + 1}-$year"
                editText.setText(selectedDate)
                onDateSelected(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}
