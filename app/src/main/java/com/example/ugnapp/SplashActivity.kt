package com.example.ugnapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logoImageView: ImageView = findViewById(R.id.logoImageView)
        supportActionBar?.hide()

        logoImageView.scaleX = 0.3f
        logoImageView.scaleY = 0.3f
        logoImageView.alpha = 0f
        logoImageView.animate()
            .scaleX(1.0f)
            .scaleY(1.0f)
            .alpha(1.0f)
            .setDuration(1000)
            .start()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LandingActivity::class.java)
            startActivity(intent)
            finish()
        }, 2700)
    }
}