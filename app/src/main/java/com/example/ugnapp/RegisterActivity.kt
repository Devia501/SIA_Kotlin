package com.example.ugnapp // Pastikan ini nama package-mu

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ugnapp.R

class RegisterActivity : AppCompatActivity() {
    private lateinit var etFullName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        etFullName = findViewById(R.id.etFullName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        registerButton = findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            handleRegister()
        }
    }
    private fun handleRegister() {
        // Ambil teks dari input
        val fullName = etFullName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showToast("Nama, Email, dan Password wajib diisi!")
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Format email tidak valid! Contoh: nama@email.com")
            return
        }
        if (password != confirmPassword) {
            showToast("Konfirmasi password tidak cocok!")
            return
        }
        if (password.length < 8) {
            showToast("Password harus minimal 8 karakter!")
            return
        }
        showToast("Registrasi berhasil! Silakan login.")
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}