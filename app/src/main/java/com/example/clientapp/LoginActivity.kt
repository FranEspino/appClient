package com.example.clientapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val tbn_login = findViewById<Button>(R.id.btn_login)
        val tv_createAccount = findViewById<TextView>(R.id.tv_createAccount)
        tv_createAccount.setOnClickListener {
            val intento1 = Intent(this, AccountActivity::class.java)
            startActivity(intento1)
        }

        tbn_login.setOnClickListener {
            val intento1 = Intent(this, MenuActivity::class.java)
            startActivity(intento1)
        }


    }
}