package com.example.project11.activitys.activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project11.R
import com.example.project11.activitys.activitys.adapter.QuizAdapter
import com.example.project11.activitys.activitys.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date


class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var navigationView: NavigationView
    lateinit var adapter: QuizAdapter
    lateinit var firestore: FirebaseFirestore
    lateinit var quizRecyvlerView: RecyclerView
    private var quizList = mutableListOf<Quiz>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quizRecyvlerView = findViewById(R.id.quizRecyclerView)
        setupFirestore()
        setUpRecyclerView()
        var datepicker = findViewById<FloatingActionButton>(R.id.btnDatepicker)
        datepicker.setOnClickListener {
            setUpDatepicker()
        }
        var toolbar: Toolbar = findViewById(R.id.topAppBar)
        var drawerLayout: DrawerLayout =
            findViewById(R.id.maindrawer) // Replace with your DrawerLayout ID
        navigationView = findViewById(R.id.navi)

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
            drawerLayout.closeDrawers()
            true

        }

        toolbar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }


    private fun setupFirestore() {
        firestore= FirebaseFirestore.getInstance()
        val collectionReference=firestore.collection("quizzes")
        collectionReference.addSnapshotListener{value,error ->
            if (value ==null||error !=null){
               Toast.makeText(this,"Error Fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }

    }

    private fun setUpRecyclerView() {
        adapter = QuizAdapter(this,quizList)
        quizRecyvlerView.layoutManager = GridLayoutManager(this, 2)
        quizRecyvlerView.adapter= adapter
    }
    private fun setUpDatepicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        datePicker.show(supportFragmentManager, "DatePicker")
        datePicker.addOnPositiveButtonClickListener {
            Log.d("DATEPICKER", datePicker.headerText)
            var intent1 =Intent(this,QuestionActivity::class.java)
            intent1.putExtra("DATE",datePicker.headerText)
            startActivity(intent1)
            var dateFormatter = SimpleDateFormat("dd-mm-yyyy")
            var date = dateFormatter.format(Date(it))
            var intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra("DATE", date)
            startActivity(intent)
        }
        datePicker.addOnNegativeButtonClickListener {
            Log.d("DATEPICKER", datePicker.headerText)
        }
        datePicker.addOnCancelListener {
            Log.d("DATEPICKER", "Date Picker Cancelled")
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}


