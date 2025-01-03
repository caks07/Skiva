package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.skiva.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import android.app.AlertDialog
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.database.DatabaseReference

class profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Inisialisasi UI
        val textViewName: TextView = findViewById(R.id.Nama)
        val textViewEmail: TextView = findViewById(R.id.Email)
        val textViewPhoneNumber: TextView = findViewById(R.id.NoTelfon)
        val editProfileButton: Button = findViewById(R.id.editProfile)
        val changePasswordButton: ImageButton = findViewById(R.id.ubahPassword)
        val deleteAccountButton: ImageButton = findViewById(R.id.hapusAkunButton)
        val logoutButton: Button = findViewById(R.id.logout)
        val backButton: ImageButton = findViewById(R.id.back)

        // Ambil UID pengguna
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId.isNullOrEmpty()) {
            Log.e("ProfileDebug", "User ID is null or empty")
            return
        }

        // Ambil data pengguna dari Firebase
        val databaseReference = FirebaseDatabase.getInstance("https://skiva-pab-default-rtdb.asia-southeast1.firebasedatabase.app")
            .getReference("users/$userId")

        databaseReference.get().addOnSuccessListener { snapshot ->
            val name = snapshot.child("name").value as? String ?: "Nama tidak ditemukan"
            val email = snapshot.child("email").value as? String ?: "Email tidak ditemukan"
            val phoneNumber = snapshot.child("phoneNumber").value as? String ?: "Nomor tidak ditemukan"

            textViewName.text = name
            textViewEmail.text = email
            textViewPhoneNumber.text = phoneNumber

            Log.d("ProfileDebug", "Data pengguna ditemukan: $name, $email, $phoneNumber")
        }.addOnFailureListener { e ->
            Log.e("ProfileDebug", "Gagal mengambil data pengguna", e)
        }

        // Navigasi kembali ke halaman sebelumnya
        backButton.setOnClickListener {
            val intent = Intent(this, home_page::class.java)
            startActivity(intent)
        }

        // Edit Profil
        editProfileButton.setOnClickListener {
            showEditProfilePopup(userId, databaseReference)
        }

        // Ubah Password
        changePasswordButton.setOnClickListener {
            showChangePasswordPopup()
        }

        // Hapus Akun
        deleteAccountButton.setOnClickListener {
            showDeleteAccountPopup(userId)
        }

        // Logout
        logoutButton.setOnClickListener {
            logoutUser()
        }
    }

    private fun logoutUser() {
        FirebaseAuth.getInstance().signOut() // Logs the user out
        val intent = Intent(this, login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun showEditProfilePopup(userId: String, databaseReference: DatabaseReference) {
        val dialogView = layoutInflater.inflate(R.layout.popup_edit_profile, null)
        val editName = dialogView.findViewById<EditText>(R.id.editName)
        val editEmail = dialogView.findViewById<EditText>(R.id.editEmail)
        val editPhone = dialogView.findViewById<EditText>(R.id.editPhone)

        AlertDialog.Builder(this)
            .setTitle("Edit Profile")
            .setView(dialogView)
            .setPositiveButton("Simpan") { dialog, _ ->
                val updatedName = editName.text.toString().trim()
                val updatedEmail = editEmail.text.toString().trim()
                val updatedPhone = editPhone.text.toString().trim()

                if (updatedName.isEmpty() || updatedEmail.isEmpty() || updatedPhone.isEmpty()) {
                    Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
                } else {
                    val updates = mapOf(
                        "name" to updatedName,
                        "email" to updatedEmail,
                        "phoneNumber" to updatedPhone
                    )
                    databaseReference.updateChildren(updates).addOnSuccessListener {
                        Toast.makeText(this, "Profil berhasil diperbarui!", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Gagal memperbarui profil.", Toast.LENGTH_SHORT).show()
                    }
                }
                dialog.dismiss()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun showChangePasswordPopup() {
        val dialogView = layoutInflater.inflate(R.layout.popup_change_password, null)
        val oldPassword = dialogView.findViewById<EditText>(R.id.oldPassword)
        val newPassword = dialogView.findViewById<EditText>(R.id.newPassword)

        AlertDialog.Builder(this)
            .setTitle("Ubah Password")
            .setView(dialogView)
            .setPositiveButton("Simpan") { dialog, _ ->
                val oldPass = oldPassword.text.toString().trim()
                val newPass = newPassword.text.toString().trim()

                if (oldPass.isEmpty() || newPass.isEmpty()) {
                    Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
                } else {
                    val user = FirebaseAuth.getInstance().currentUser
                    val email = user?.email

                    if (email != null) {
                        val credential = EmailAuthProvider.getCredential(email, oldPass)
                        user?.reauthenticate(credential)?.addOnSuccessListener {
                            // Update password di Firebase Authentication
                            user.updatePassword(newPass)?.addOnSuccessListener {
                                Toast.makeText(this, "Password berhasil diperbarui!", Toast.LENGTH_SHORT).show()

                                // Update password di Realtime Database
                                val userId = user.uid
                                val databaseReference = FirebaseDatabase.getInstance()
                                    .getReference("users/$userId")
                                databaseReference.child("password").setValue(newPass)
                                    .addOnSuccessListener {
                                        Log.d("ChangePassword", "Password berhasil diperbarui di database.")
                                    }
                                    .addOnFailureListener { e ->
                                        Log.e("ChangePassword", "Gagal memperbarui password di database: ", e)
                                    }
                            }?.addOnFailureListener { e ->
                                Toast.makeText(this, "Gagal memperbarui password di Firebase Authentication.", Toast.LENGTH_SHORT).show()
                                Log.e("ChangePassword", "Error updating password: ", e)
                            }
                        }?.addOnFailureListener { e ->
                            Toast.makeText(this, "Password lama salah.", Toast.LENGTH_SHORT).show()
                            Log.e("ChangePassword", "Reauthenticate failed: ", e)
                        }
                    } else {
                        Toast.makeText(this, "Gagal mendapatkan email pengguna.", Toast.LENGTH_SHORT).show()
                        Log.e("ChangePassword", "User email is null.")
                    }
                }
                dialog.dismiss()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }



    private fun showDeleteAccountPopup(userId: String) {
        AlertDialog.Builder(this)
            .setTitle("Hapus Akun")
            .setMessage("Apakah Anda yakin ingin menghapus akun ini? Tindakan ini tidak dapat dibatalkan.")
            .setPositiveButton("Ya") { dialog, _ ->
                val user = FirebaseAuth.getInstance().currentUser
                val databaseReference = FirebaseDatabase.getInstance()
                    .getReference("users/$userId")

                if (user != null) {
                    databaseReference.removeValue()
                        .addOnSuccessListener {
                            user.delete()
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Akun berhasil dihapus.", Toast.LENGTH_SHORT).show()
                                    FirebaseAuth.getInstance().signOut()
                                    val intent = Intent(this, login::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivity(intent)
                                    finish()
                                }
                                .addOnFailureListener { e ->
                                    Log.e("DeleteAccount", "Gagal menghapus akun dari Authentication.", e)
                                    Toast.makeText(this, "Gagal menghapus akun. Coba lagi.", Toast.LENGTH_SHORT).show()
                                }
                        }
                        .addOnFailureListener { e ->
                            Log.e("DeleteAccount", "Gagal menghapus data pengguna dari database.", e)
                            Toast.makeText(this, "Gagal menghapus data pengguna. Coba lagi.", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this, "Gagal menghapus akun: pengguna tidak ditemukan.", Toast.LENGTH_SHORT).show()
                    Log.e("DeleteAccount", "FirebaseAuth currentUser is null.")
                }
                dialog.dismiss()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}
