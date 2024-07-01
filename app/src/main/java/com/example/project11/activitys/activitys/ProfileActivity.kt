package com.example.project11.activitys.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project11.R
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        firebaseAuth = FirebaseAuth.getInstance()
        val txtEmail = findViewById<TextView>(R.id.txtEmail)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        txtEmail.text = firebaseAuth.currentUser?.email
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LonIn::class.java)
            startActivity(intent)
            finish()
        }
    }
}


/*
package com.example.project11.activitys.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project11.R
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        firebaseAuth = FirebaseAuth.getInstance()
        var txtEmail =findViewById<TextView>(R.id.txtEmail)
        var btnLogout = findViewById<Button>(R.id.btnLogout)
        txtEmail.text = firebaseAuth.currentUser?.email
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LonIn::class.java)
            startActivity(intent)
        }
    }
}
 */