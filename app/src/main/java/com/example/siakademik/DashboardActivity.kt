package com.yourapp.pendaftar

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.siakademik.R


// Data class untuk menu item kustom
data class CustomMenuItem(val id: Int, val iconResId: Int, val title: String)

class DashboardActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var customDrawerMenuContainer: LinearLayout
    private lateinit var userName: String

    // Data untuk menu kustom
    private val drawerMenuItems = listOf(
        // Catatan: Menggunakan R.drawable.home sebagai pengganti R.drawable.ic_dashboard yang mungkin tidak ada.
        CustomMenuItem(R.id.nav_dashboard, R.drawable.home, "Dashboard"),
        CustomMenuItem(R.id.nav_pendaftaran, R.drawable.clarity_form_line, "Pendaftaran"),
        CustomMenuItem(R.id.nav_status_pendaftaran, R.drawable.fluent_shifts, "Status Pendaftaran"),
        CustomMenuItem(R.id.nav_profile, R.drawable.user_circle, "Profile")
    )

    // Untuk melacak item yang sedang aktif
    private var activeMenuItemId: Int = R.id.nav_dashboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        userName = intent.getStringExtra("USER_NAME") ?: "Calon Mahasiswa"

        setupViews()
        setupListeners()
        setupCustomDrawerMenu()
    }

    private fun setupViews() {
        drawerLayout = findViewById(R.id.drawer_layout)
        customDrawerMenuContainer = findViewById(R.id.custom_drawer_menu_container)

        val avatarText = findViewById<TextView>(R.id.avatar_text)
        val userNameText = findViewById<TextView>(R.id.user_name)

        avatarText.text = getInitials(userName)
        userNameText.text = userName
    }

    private fun setupListeners() {
        // Menu button
        findViewById<ImageView>(R.id.menu_button).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Notification button
        findViewById<ImageView>(R.id.notif_button).setOnClickListener {
            // Handle notification click
        }

        // Quick action buttons
        findViewById<CardView>(R.id.action_tata_cara).setOnClickListener {
            navigateToTataCara()
        }

        findViewById<CardView>(R.id.action_informasi).setOnClickListener {
            navigateToInformasi()
        }

        // Register button
        findViewById<CardView>(R.id.register_button).setOnClickListener {
            navigateToTataCara()
        }

        // News card button
        findViewById<CardView>(R.id.news_card_button).setOnClickListener {
            // Handle news click
        }

        // Bottom navigation listeners (Memicu update visual di drawer)
        // nav_home mengarah ke nav_dashboard
        findViewById<View>(R.id.nav_home).setOnClickListener {
            updateActiveDrawerItem(R.id.nav_dashboard)
        }

        // nav_form mengarah ke nav_pendaftaran
        findViewById<View>(R.id.nav_form).setOnClickListener {
            navigateToTataCara()
            updateActiveDrawerItem(R.id.nav_pendaftaran)
        }

        // nav_status mengarah ke nav_status_pendaftaran
        findViewById<View>(R.id.nav_status).setOnClickListener {
            navigateToStatus()
            updateActiveDrawerItem(R.id.nav_status_pendaftaran)
        }

        // nav_profile
        findViewById<View>(R.id.nav_profile).setOnClickListener {
            navigateToProfile()
            updateActiveDrawerItem(R.id.nav_profile)
        }
    }

    private fun setupCustomDrawerMenu() {
        val inflater = LayoutInflater.from(this)
        customDrawerMenuContainer.removeAllViews()

        drawerMenuItems.forEach { menuItem ->
            val itemView = inflater.inflate(R.layout.drawer_custom_menu_item, customDrawerMenuContainer, false)
            val iconView = itemView.findViewById<ImageView>(R.id.drawer_item_icon)
            val titleView = itemView.findViewById<TextView>(R.id.drawer_item_title)

            iconView.setImageResource(menuItem.iconResId)
            titleView.text = menuItem.title

            itemView.setOnClickListener {
                updateActiveDrawerItem(menuItem.id)
                handleDrawerMenuItemClick(menuItem.id)
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            itemView.tag = menuItem.id

            customDrawerMenuContainer.addView(itemView)
        }

        // Set item Dashboard sebagai aktif secara default saat pertama kali dimuat
        updateActiveDrawerItem(activeMenuItemId)
    }

    private fun updateActiveDrawerItem(newActiveId: Int) {

        val exists = drawerMenuItems.any { it.id == newActiveId }
        if (!exists) return

        activeMenuItemId = newActiveId

        for (i in 0 until customDrawerMenuContainer.childCount) {
            val itemView = customDrawerMenuContainer.getChildAt(i)
            val innerPill = itemView.findViewById<LinearLayout>(R.id.drawer_item_inner_pill)
            val iconView = itemView.findViewById<ImageView>(R.id.drawer_item_icon)
            val titleView = itemView.findViewById<TextView>(R.id.drawer_item_title)

            // Asumsi R.color.green_dark adalah warna hijau gelap Anda (#015023)
            val colorGreenDark = ContextCompat.getColor(this, R.color.green_dark)

            if (itemView.tag == newActiveId) {
                // Tampilan AKTIF
                innerPill.setBackgroundResource(R.drawable.drawer_item_bg_active)
                iconView.setColorFilter(colorGreenDark)
                titleView.setTextColor(colorGreenDark)
            } else {
                // Tampilan NON-AKTIF
                innerPill.setBackgroundResource(R.drawable.drawer_item_bg_inactive)
                iconView.setColorFilter(Color.WHITE)
                titleView.setTextColor(Color.WHITE)
            }
        }
    }

    private fun handleDrawerMenuItemClick(itemId: Int) {
        when (itemId) {
            R.id.nav_dashboard -> {
                // Logika Dashboard (sudah di home)
            }
            R.id.nav_pendaftaran -> {
                navigateToTataCara()
            }
            R.id.nav_status_pendaftaran -> {
                navigateToStatus()
            }
            R.id.nav_profile -> {
                navigateToProfile()
            }
        }
        // drawerLayout.closeDrawer(GravityCompat.START) - Dihapus karena sudah ada di setOnClickListener
    }

    private fun getInitials(name: String): String {
        val parts = name.trim().split(" ")
        return when {
            parts.size >= 2 -> {
                "${parts[0].first().uppercaseChar()}${parts[1].first().uppercaseChar()}"
            }
            parts.size == 1 && parts[0].isNotEmpty() -> {
                parts[0].first().uppercaseChar().toString()
            }
            else -> "CM"
        }
    }

    private fun navigateToTataCara() { /* Intent to TataCaraActivity */ }
    private fun navigateToInformasi() { /* Intent to InformasiActivity */ }
    private fun navigateToStatus() { /* Intent to StatusActivity */ }
    private fun navigateToProfile() { /* Intent to ProfileActivity */ }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}