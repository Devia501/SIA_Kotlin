package com.example.sia
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class DashboardAdmin : AppCompatActivity() {


    private var userName: String? = "Admin Utama"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_admin)

        val greetingText: TextView = findViewById(R.id.greeting_text)
        val btnLogout: ImageButton = findViewById(R.id.btn_logout_icon)
        val btnKelolaPendaftaran: Button = findViewById(R.id.btn_kelola_pendaftaran)
        val btnKelolaManager: Button = findViewById(R.id.btn_kelola_manager)

        greetingText.text = "Selamat datang, ${userName ?: "admin system"}!"

        btnLogout.setOnClickListener {
            handleLogout()
        }

        btnKelolaPendaftaran.setOnClickListener {
            Toast.makeText(this, "Navigasi ke Kelola Pendaftaran", Toast.LENGTH_SHORT).show()
        }

        btnKelolaManager.setOnClickListener {
            Toast.makeText(this, "Navigasi ke Kelola Manager", Toast.LENGTH_SHORT).show()
        }

    }

    private fun handleLogout() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar dari akun admin?")
            .setPositiveButton("Ya, Keluar") { dialog, which ->
                performLogout()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun performLogout() {
        Toast.makeText(this, "Logout berhasil.", Toast.LENGTH_SHORT).show()
    }
}