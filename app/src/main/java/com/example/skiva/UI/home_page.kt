package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.model.JudulArtikel
import com.google.firebase.database.FirebaseDatabase

class home_page : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<JudulArtikel>
    private lateinit var imageList: Array<Int>
    private lateinit var titleList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        // Ambil userId dari SharedPreferences
        val sharedPreferences = getSharedPreferences("USER_SESSION", MODE_PRIVATE)
        val userId = sharedPreferences.getString("USER_ID", null)
        val textViewName: TextView = findViewById(R.id.textViewName)

        if (userId == null) {
            textViewName.text = "Hai, User"
            Toast.makeText(this, "User session not found. Please login again.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, login::class.java))
            finish()
        } else {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users/$userId")
            databaseReference.child("name").get().addOnSuccessListener { snapshot ->
                val name = snapshot.value as? String ?: "User"
                textViewName.text = "Hai, $name"
            }.addOnFailureListener {
                textViewName.text = "Hai, User"
                Toast.makeText(this, "Failed to fetch user name.", Toast.LENGTH_SHORT).show()
            }
        }


        // Setup RecyclerView
        imageList = arrayOf(
            R.drawable.headache,
            R.drawable.sore_throat,
            R.drawable.demam,
            R.drawable.batuk,
            R.drawable.sesak_napas,
            R.drawable.gabisa_nyium_bau
        )

        titleList = arrayOf(
            "Constant Headache",
            "Sore Throat",
            "Elevated Temperature",
            "Severe Coughing",
            "Difficulty Breathing",
            "Loss of Sense of Smell"
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf()
        getData()

        // Navigasi ke halaman pengingat obat
        val pengingatButton: ImageButton = findViewById(R.id.imageButtonPengingat)
        pengingatButton.setOnClickListener {
            val intent = Intent(this, pengingat_obat::class.java)
            startActivity(intent)
        }

        // Navigasi ke halaman kategori penyakit
        val imageWajah: ImageButton = findViewById(R.id.imageWajah)
        imageWajah.setOnClickListener {
            val intent = Intent(this, kategori_penyakit::class.java)
            startActivity(intent)
        }

        // Navigasi ke halaman screening
        val buttonScreening: ImageButton = findViewById(R.id.buttonScreening)
        buttonScreening.setOnClickListener {
            val intent = Intent(this, screening::class.java)
            startActivity(intent)
        }

        // Navigasi ke halaman badge
        val buttonbadge: ImageButton = findViewById(R.id.ButtonBadge)
        buttonbadge.setOnClickListener {
            val intent = Intent(this, badge::class.java)
            startActivity(intent)
        }

        // Navigasi ke halaman badge
        val buttonChat: ImageButton = findViewById(R.id.konsultasi)
        buttonChat.setOnClickListener {
            val intent = Intent(this, Chat::class.java)
            startActivity(intent)
        }
    }

    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = JudulArtikel(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = JudulArtikelAdapter(dataList)
    }
}
