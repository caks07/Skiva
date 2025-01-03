package com.example.skiva.UI

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skiva.R
import java.io.ByteArrayOutputStream
import java.io.InputStream

class citra_medis : AppCompatActivity() {

    private lateinit var uploadButton: ImageButton
    private lateinit var cameraButton: ImageButton
    private lateinit var submitButton: Button
    private lateinit var additionalNotes: EditText
    private var selectedBitmap: Bitmap? = null
    private val CAMERA_REQUEST_CODE = 101
    private val GALLERY_REQUEST_CODE = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citra_medis)

        uploadButton = findViewById(R.id.buttonupload)
        cameraButton = findViewById(R.id.buttoncamera)
        submitButton = findViewById(R.id.buttonSubmit)
        additionalNotes = findViewById(R.id.editCatatan)

        uploadButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, GALLERY_REQUEST_CODE)
        }

        cameraButton.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }

        submitButton.setOnClickListener {
            val notes = additionalNotes.text.toString()
            if (selectedBitmap == null && notes.isEmpty()) {
                Toast.makeText(this, "Harap unggah foto atau masukkan catatan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val imageBase64 = selectedBitmap?.let { bitmapToBase64(it) } ?: ""

            val intent = Intent(this, result_screening::class.java).apply {
                putExtra("imageBase64", imageBase64)
                putExtra("notes", notes)
                putStringArrayListExtra("symptoms", intent.getStringArrayListExtra("symptoms"))
            }
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    val imageUri: Uri = data?.data ?: return
                    val inputStream: InputStream = contentResolver.openInputStream(imageUri)!!
                    selectedBitmap = BitmapFactory.decodeStream(inputStream)
                    Toast.makeText(this, "Gambar berhasil diunggah", Toast.LENGTH_SHORT).show()
                }
                CAMERA_REQUEST_CODE -> {
                    selectedBitmap = data?.extras?.get("data") as Bitmap
                    Toast.makeText(this, "Gambar berhasil diambil", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }
}
