package com.example.project11.activitys.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project11.R
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.FirebaseAuth


class LonIn : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lon_in)
        firebaseAuth = FirebaseAuth.getInstance()
        val button1: MaterialTextView = findViewById(R.id.btnSignUp)
        button1.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }
        val button2: Button = findViewById(R.id.btnLogin)
        button2.setOnClickListener {
            login()
        }

    }

    private fun login() {
        val email = getString(R.id.etEmailAddress).toString()
        val password = getString(R.id.etPassword).toString()
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
