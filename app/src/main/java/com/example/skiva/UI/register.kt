package com.example.skiva.UI

import RegisterViewModel
import RegisterViewModelFactory
import com.example.skiva.repository.UserRepository
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.skiva.R

class register : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var registerButton: Button

    private val viewModel: RegisterViewModel by viewModels {
        RegisterViewModelFactory(UserRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI elements
        nameEditText = findViewById(R.id.isiNama)
        emailEditText = findViewById(R.id.isiEmail)
        phoneEditText = findViewById(R.id.isiNomor)
        passwordEditText = findViewById(R.id.isiPassword)
        confirmPasswordEditText = findViewById(R.id.isiUlangPassword)
        registerButton = findViewById(R.id.register)

        // Observe register status
        viewModel.registerStatus.observe(this, Observer { isSuccess ->
            if (isSuccess) {
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, login::class.java))
                finish()
            }
        })

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })

        // Set up button click listener
        registerButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.registerUser(name, email, phone, password, confirmPassword)
        }

        val createAccountTextView: TextView = findViewById(R.id.login)
        createAccountTextView.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
    }
}
