package com.example.project11.activitys.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project11.R
import com.google.firebase.auth.FirebaseAuth

class logIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_intro)
        val auth : FirebaseAuth = FirebaseAuth.getInstance()
        if(auth.currentUser !=null){
            Toast.makeText(this, "User is already loggde in!!",Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }
        val button2: Button = findViewById(R.id.getstart)
        button2.setOnClickListener {
            redirect("LOGIN")
        }
    }
    private fun redirect(name:String){
        val intent = when(name){
            "LOGIN" -> Intent(this, LonIn::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("no path exists")
        }
        startActivity(intent)
        finish()
    }
}