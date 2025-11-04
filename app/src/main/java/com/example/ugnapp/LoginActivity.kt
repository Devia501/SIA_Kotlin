package com.example.ugnapp

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ugnapp.R
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var captchaCheckbox: CheckBox
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        captchaCheckbox = findViewById(R.id.captchaCheckbox)
        loginButton = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            handleLogin()
        }
    }
    private fun handleLogin() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            showToast("Email dan Password tidak boleh kosong!")
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Format email tidak valid! Contoh: nama@email.com")
            return
        }
        if (!captchaCheckbox.isChecked) {
            showToast("Harap centang captcha terlebih dahulu.")
            return
        }
        showToast("Login berhasil! (Simulasi)")
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}