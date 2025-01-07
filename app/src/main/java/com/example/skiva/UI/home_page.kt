package com.example.skiva.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skiva.R
import com.example.skiva.adapter.NavigationController
import com.example.skiva.model.JudulArtikel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class home_page : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<JudulArtikel>
    private lateinit var imageList: Array<Int>
    private lateinit var titleList: Array<String>
    private lateinit var descriptions: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        // Inisialisasi UI
        val textViewName: TextView = findViewById(R.id.textViewName)
        recyclerView = findViewById(R.id.recyclerView)

        // Ambil UID pengguna
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId.isNullOrEmpty()) {
            textViewName.text = "Hai, User"
            Log.e("FirebaseDebug", "User ID is null or empty")
            return
        }

        // Ambil nama pengguna dari Firebase
        val databaseReference = FirebaseDatabase.getInstance("https://skiva-pab-default-rtdb.asia-southeast1.firebasedatabase.app")
            .getReference("users/$userId")
        databaseReference.child("name").get().addOnSuccessListener { snapshot ->
            val name = snapshot.value as? String ?: "User"
            textViewName.text = "Hai, $name"
            Log.d("FirebaseDebug", "Nama ditemukan di Firebase: $name")
        }.addOnFailureListener { e ->
            textViewName.text = "Hai, User"
            Log.e("FirebaseDebug", "Gagal mengambil nama dari Firebase", e)
        }

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

// Dummy Data
        imageList = arrayOf(
            R.drawable.headache,
            R.drawable.sore_throat,
            R.drawable.demam,
            R.drawable.batuk,
            R.drawable.sesak_napas,
            R.drawable.gabisa_nyium_bau
        )

        titleList = arrayOf(
            "Rahasia Kulit Sehat",
            "Penyakit Kulit Umum",
            "Tren Skincare Alami",
            "Mengenal Eksim Lebih Dalam",
            "Jerawat di Usia Dewasa",
            "Kulit Kusam vs Cerah"
        )

        descriptions = arrayOf(
            "Rahasia memiliki kulit sehat dan bercahaya tidak hanya terletak pada produk mahal, tetapi juga pada konsistensi perawatan. Memahami jenis kulit Anda adalah langkah pertama yang penting. Setiap jenis kulit, baik itu kering, berminyak, kombinasi, atau sensitif, membutuhkan pendekatan berbeda. Selain itu, membersihkan wajah dengan lembut, menjaga kelembapan, dan melindungi dari sinar UV adalah langkah dasar yang tidak boleh dilewatkan.\n" +
                    "\n" +
                    "Nutrisi juga memainkan peran penting. Konsumsi makanan kaya antioksidan, seperti buah-buahan dan sayuran, dapat membantu memperbaiki kulit dari dalam. Tidur yang cukup dan manajemen stres juga penting untuk mencegah munculnya masalah kulit, seperti jerawat dan lingkaran hitam. Dengan mengikuti rutinitas perawatan yang tepat dan gaya hidup sehat, Anda dapat menjaga kulit tetap sehat dan bercahaya sepanjang hari.",
            "Penyakit kulit adalah salah satu kondisi medis yang paling sering dialami oleh banyak orang. Mulai dari eksim, psoriasis, hingga infeksi jamur, masing-masing memiliki gejala dan penyebab yang berbeda. Eksim, misalnya, sering muncul sebagai ruam kering dan gatal yang disebabkan oleh alergi atau iritasi lingkungan. Sementara itu, psoriasis adalah kondisi autoimun yang menyebabkan kulit mengelupas dan menebal.\n" +
                    "\n" +
                    "Penting untuk mengenali gejala penyakit kulit sedini mungkin agar dapat mengobatinya secara efektif. Penggunaan krim topikal, obat oral, atau terapi cahaya sering digunakan untuk mengatasi penyakit ini. Jangan lupa bahwa menjaga kebersihan kulit dan menghindari pemicu, seperti alergi makanan atau produk tertentu, dapat membantu mencegah kambuhnya kondisi ini. Konsultasikan dengan dokter kulit jika Anda mengalami masalah kulit yang sulit diatasi.",
            "Produk skincare berbasis bahan alami semakin populer karena dianggap lebih aman dan ramah lingkungan. Bahan-bahan seperti lidah buaya, minyak kelapa, dan teh hijau kini menjadi pilihan utama dalam berbagai produk perawatan kulit. Lidah buaya, misalnya, dikenal karena sifatnya yang menenangkan dan melembapkan, sementara teh hijau kaya akan antioksidan yang membantu melawan penuaan dini.\n" +
                    "\n" +
                    "Namun, tidak semua bahan alami cocok untuk setiap jenis kulit. Misalnya, minyak kelapa yang populer untuk kulit kering mungkin terlalu berat bagi kulit berminyak. Selain itu, penting untuk memilih produk dengan label \"alami\" yang memiliki sertifikasi resmi agar terhindar dari klaim palsu. Tren ini menunjukkan bahwa semakin banyak orang kini beralih ke perawatan yang lebih alami dan minimalis.",
            "Eksim adalah salah satu masalah kulit yang sering disalahpahami. Kondisi ini ditandai dengan kulit kering, gatal, dan sering kali meradang. Eksim biasanya dipicu oleh alergi, stres, atau faktor lingkungan, seperti udara dingin atau kering. Meski sering dialami oleh anak-anak, kondisi ini juga dapat bertahan hingga dewasa jika tidak diobati dengan baik.\n" +
                    "\n" +
                    "Mengelola eksim membutuhkan perawatan yang konsisten. Pelembap adalah kunci utama untuk menjaga kulit tetap terhidrasi dan mencegah kambuhnya gejala. Selain itu, menghindari alergen, seperti sabun keras atau bahan sintetis, juga dapat membantu. Jika gejala semakin parah, konsultasikan dengan dokter untuk mendapatkan obat kortikosteroid atau terapi lainnya.",
            "Jerawat bukan hanya masalah remaja. Banyak orang dewasa juga mengalaminya, terutama wanita yang mengalami fluktuasi hormon. Jerawat pada usia dewasa biasanya muncul di area rahang dan dagu, sering kali terkait dengan stres, pola makan, atau penggunaan produk yang tidak sesuai dengan jenis kulit.\n" +
                    "\n" +
                    "Perawatan jerawat dewasa membutuhkan pendekatan yang berbeda. Produk dengan kandungan asam salisilat atau retinol sering direkomendasikan untuk mengatasi jerawat tanpa mengiritasi kulit. Selain itu, menjaga kebersihan wajah, menghindari produk berat, dan mengelola stres dapat membantu mencegah munculnya jerawat baru. Konsultasi dengan dokter kulit juga penting untuk menangani kasus yang lebih kompleks.",
            "Kulit kusam sering kali disebabkan oleh penumpukan sel kulit mati, dehidrasi, atau kurangnya perlindungan terhadap sinar UV. Kondisi ini membuat kulit terlihat lelah dan tidak bercahaya. Untungnya, dengan perawatan yang tepat, Anda dapat mengembalikan kecerahan kulit Anda.\n" +
                    "\n" +
                    "Eksfoliasi secara teratur menggunakan scrub lembut atau bahan kimia seperti AHA dapat membantu menghilangkan sel kulit mati dan mendorong regenerasi kulit. Jangan lupa untuk menjaga kulit tetap lembap dengan pelembap yang sesuai, serta gunakan tabir surya setiap hari untuk melindungi dari kerusakan akibat sinar matahari. Kulit cerah bukan hanya tentang penampilan, tetapi juga tentang kesehatan kulit yang optimal."
        )

        dataList = ArrayList()
        getData()

// Navigation Setup
        setupNavigation()
    }

    private fun getData() {
        if (imageList.size != titleList.size || titleList.size != descriptions.size) {
            throw IllegalStateException("Data arrays must have the same length.")
        }

        for (i in imageList.indices) {
            val dataClass = JudulArtikel(imageList[i], titleList[i], descriptions[i])
            dataList.add(dataClass)
        }

        recyclerView.adapter = JudulArtikelAdapter(dataList) { artikel ->
            val intent = Intent(this, desc_artikel::class.java)
            intent.putExtra("TITLE", artikel.dataTitle)
            intent.putExtra("DESCRIPTION", artikel.description)
            startActivity(intent)
        }
    }



    private fun navigateToCategory(category: String) {
        val intent = Intent(this, kategori_penyakit::class.java)
        intent.putExtra("CATEGORY", category)
        startActivity(intent)
    }

    private fun setupNavigation() {

        val imageWajah: ImageButton = findViewById(R.id.imageWajah)
        val imageMenular: ImageButton = findViewById(R.id.imageMenular)
        val imageTidakMenular: ImageButton = findViewById(R.id.imageTidakMenular)
        val imageLainnya: ImageButton = findViewById(R.id.imageLainnya)

        imageWajah.setOnClickListener { navigateToCategory("Wajah") }
        imageMenular.setOnClickListener { navigateToCategory("Menular") }
        imageTidakMenular.setOnClickListener { navigateToCategory("Tidak Menular") }
        imageLainnya.setOnClickListener { navigateToCategory("Lainnya") }


        val pengingatButton: ImageButton = findViewById(R.id.imageButtonPengingat)
        pengingatButton.setOnClickListener {
            val intent = Intent(this, pengingat_obat::class.java)
            startActivity(intent)
        }

        val buttonScreening: ImageButton = findViewById(R.id.buttonScreening)
        buttonScreening.setOnClickListener {
            val intent = Intent(this, screening::class.java)
            startActivity(intent)
        }

        val buttonBadge: ImageButton = findViewById(R.id.ButtonBadge)
        buttonBadge.setOnClickListener {
            val intent = Intent(this, badge::class.java)
            startActivity(intent)
        }

        val buttonChat: ImageButton = findViewById(R.id.konsultasi)
        buttonChat.setOnClickListener {
            val intent = Intent(this, layanan_konsultasi::class.java)
            startActivity(intent)
        }

        val buttonProfil: ImageButton = findViewById(R.id.b_profile)
        buttonProfil.setOnClickListener {
            val intent = Intent(this, profile::class.java)
            startActivity(intent)
        }

        val buttonLaporan: ImageButton = findViewById(R.id.laporan)
        buttonLaporan.setOnClickListener {
            val intent = Intent(this, laporan::class.java)
            startActivity(intent)
        }
        val buttonPromo: ImageButton = findViewById(R.id.CekPromo)
        buttonPromo.setOnClickListener {
            val intent = Intent(this, promo::class.java)
            startActivity(intent)
        }

        val buttonPromo1: ImageButton = findViewById(R.id.imagepromo)
        buttonPromo1.setOnClickListener {
            val intent = Intent(this, promo::class.java)
            startActivity(intent)
        }

        val buttonprogress: ImageButton = findViewById(R.id.imageProgress)
        buttonprogress.setOnClickListener {
            val intent = Intent(this, pengingat_obat::class.java)
            startActivity(intent)
        }


        val buttonSkincare: ImageButton = findViewById(R.id.skincare)
        buttonSkincare.setOnClickListener {
            val intent = Intent(this, pengingat_skincare::class.java)
            startActivity(intent)
        }

        val buttonArtikel: ImageButton = findViewById(R.id.link_selengkapnya)
        buttonArtikel.setOnClickListener {
            val intent = Intent(this, artikel::class.java)
            startActivity(intent)
        }

        val buttonNotif: ImageButton = findViewById(R.id.notif)
        buttonNotif.setOnClickListener {
            val intent = Intent(this, notifikasi::class.java)
            startActivity(intent)
        }

        val navigationView = findViewById<View>(R.id.header_shortcut_include)
        NavigationController(this, navigationView)
    }
}
