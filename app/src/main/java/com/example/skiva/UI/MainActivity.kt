package com.example.skiva.UI
//Muhammad Hidayat Arga Wicaksana - 22523071
//Radyan Laksmana WIrawan - 22523167
//Wisnu Arya Pradipta - 22523241
//Aditama Bayu Nugroho - 22523046
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.skiva.R
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
        val database = FirebaseDatabase.getInstance("https://skiva-pab-default-rtdb.asia-southeast1.firebasedatabase.app")
        val myRef = database.getReference("message")

        val tvTest: TextView = findViewById(R.id.tvTest)

        myRef.setValue("Hello, World!")

        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()
                tvTest.text = value
                Log.d("Firebase", "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Firebase", "Failed to read value.", error.toException())
            }
        })
    }
}