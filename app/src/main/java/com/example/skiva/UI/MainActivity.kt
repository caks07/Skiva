package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.skiva.R
import com.example.skiva.utils.DatabaseSeeder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val seeder = DatabaseSeeder()
        seeder.seedData(
            onSuccess = {
                Log.d("DatabaseSeeder", "Data berhasil ditambahkan ke Firebase Realtime Database.")
            },
            onFailure = { errorMessage ->
                Log.e("DatabaseSeeder", "Gagal menambahkan data: $errorMessage")
            }
        )

        // Example test to read a simple message from Firebase
        val database = FirebaseDatabase.getInstance("https://skiva-pab-default-rtdb.asia-southeast1.firebasedatabase.app")
        val myRef = database.getReference("message")

        val tvTest: TextView = findViewById(R.id.tvTest)

        myRef.setValue("Hello, World!")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                tvTest.text = value
                Log.d("Firebase", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Firebase", "Failed to read value.", error.toException())
            }
        })

        // Tambahkan logika navigasi
        findViewById<ImageButton>(R.id.homeButton).setOnClickListener {
            val intent = Intent(this, home_page::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.reminderButton).setOnClickListener {
            val intent = Intent(this, pengingat_obat::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.screeningButton).setOnClickListener {
            val intent = Intent(this, screening::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.reportButton).setOnClickListener {
            val intent = Intent(this, laporan::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.profileButton).setOnClickListener {
            val intent = Intent(this, profile::class.java)
            startActivity(intent)
        }
    }
}
