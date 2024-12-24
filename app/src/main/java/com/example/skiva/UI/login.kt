package com.example.skiva.UI

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.skiva.R
import com.example.skiva.repository.AuthRepository
import com.example.skiva.viewModel.LoginViewModel
import com.example.skiva.viewModel.LoginViewModelFactory
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class login : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(AuthRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val sharedPreferences = getSharedPreferences("USER_SESSION", MODE_PRIVATE)
        val emailEditText: EditText = findViewById(R.id.enterEmail)
        val passwordEditText: EditText = findViewById(R.id.enterPassword)
        val loginButton: Button = findViewById(R.id.Login)
        val googleButton: ImageButton = findViewById(R.id.google)

        val createAccountTextView: TextView = findViewById(R.id.createAccount)
        createAccountTextView.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }


        // Email/Password Login
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.loginWithEmail(email, password)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Google Login
        googleButton.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, 100)
        }

        viewModel.loginStatus.observe(this) { isSuccess ->
            if (isSuccess) {
                val userId = viewModel.getUserId()
                if (userId != null) {
                    sharedPreferences.edit().putString("USER_ID", userId).apply() // Simpan userId
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, home_page::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Failed to retrieve user session", Toast.LENGTH_SHORT).show()
                }
            }
        }


        viewModel.errorMessage.observe(this) { errorMessage ->
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        // Google Sign-In setup
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.AIzaSyBubYYFS_n2MkhAHkYbFZL7OhUKDfaq4LA))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            task.addOnSuccessListener { account ->
                viewModel.loginWithGoogle(account.idToken!!)
            }.addOnFailureListener {
                Toast.makeText(this, "Google Sign-In Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
