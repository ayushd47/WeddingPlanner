package com.reshika.foodmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DashboardActivity : AppCompatActivity() {
    private lateinit var btnhome:Button

    private lateinit var btnorder:Button
    private lateinit var btncart:Button
    private lateinit var btnprofile:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btnhome= findViewById(R.id.btnhome)


        btncart = findViewById(R.id.btncart)
        btnprofile = findViewById(R.id.btnprofile)

        btnhome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        btncart.setOnClickListener {
            startActivity(Intent(this, ViewActivity::class.java))
        }





  }
}