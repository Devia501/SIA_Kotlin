package com.example.sia

import android.os.Bundle
import android.widget.Button // Diperlukan untuk mengakses tombol
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // BARIS INI MENAMPILKAN DASHBOARD ADMIN
        setContentView(R.layout.activity_dashboard_admin)

        // Mengatur insets untuk tampilan layar penuh
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- Contoh Pengait (Binding) Elemen UI ---

        // 1. Mengaitkan Tombol "Kelola Pendaftaran"
        val btnKelolaPendaftaran = findViewById<Button>(R.id.btn_kelola_pendaftaran)

        // 2. Menetapkan Aksi Klik
        btnKelolaPendaftaran.setOnClickListener {
            Toast.makeText(this, "Tombol Kelola Pendaftaran diklik!", Toast.LENGTH_SHORT).show()
            // Di sini Anda akan menambahkan Intent untuk pindah Activity
        }
    }
}