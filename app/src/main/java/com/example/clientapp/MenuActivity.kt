package com.example.clientapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val item_menu_ayuda = findViewById<LinearLayout>(R.id.ly_ayuda)
        item_menu_ayuda.setOnClickListener {
            val intento1 = Intent(this, AboutActivity::class.java)
            startActivity(intento1)
        }

        val item_menu_solicitar = findViewById<LinearLayout>(R.id.ly_solicitar)
        item_menu_solicitar.setOnClickListener {
            val intento1 = Intent(this, UbicationActivity::class.java)
            startActivity(intento1)
        }

    }
}