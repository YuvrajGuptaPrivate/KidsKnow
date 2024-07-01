package com.example.project11.activitys.activitys

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project11.R
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConPass: EditText
    private lateinit var auth: FirebaseAuth

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        etConPass=findViewById(R.id.etConPassword)
        etEmail = findViewById(R.id.etEmailAddress)
        etPassword = findViewById(R.id.etPassword)

        auth = Firebase.auth
        val button2: MaterialTextView = findViewById(R.id.btnLogin)
        button2.setOnClickListener {
            val intent = Intent(this, LonIn::class.java) // Corrected activity name
            startActivity(intent)
            finish()
        }
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        btnSignUp.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = getString(R.id.etConPassword).toString()
            if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()

            }
            else if (password != confirmPassword) {
                Toast.makeText(this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show()

            }
            else
            {

            }
            // Create a new user with email and password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                        val firebaseUser = auth.currentUser
                    }
                    else {
                        Toast.makeText(this, "Error creating user: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}