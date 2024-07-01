package com.example.project11.activitys.activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project11.R
import com.example.project11.activitys.activitys.adapter.OptionAdapter
import com.example.project11.activitys.activitys.models.Questions
import com.example.project11.activitys.activitys.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson


class QuestionActivity : AppCompatActivity() {
    lateinit var firestore: FirebaseFirestore
    lateinit var description: TextView
    lateinit var optionList: RecyclerView
    var layoutManager = LinearLayoutManager(this)
    var quizzes: MutableList<Quiz>? = null
    var questions: MutableMap<String, Questions>? = null
    var index = 1
    lateinit var Previous: Button
    lateinit var Submit: Button
    lateinit var Next: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        firestore = FirebaseFirestore.getInstance()
        layoutManager = LinearLayoutManager(this)// Correct layout resource ID
        optionList = findViewById(R.id.optionList)
        description = findViewById(R.id.description)
        Previous = findViewById(R.id.btnPrevious)
        Submit = findViewById(R.id.btnSubmit)
        Next = findViewById(R.id.btnNext)

        val intent = intent // Get the intent
        setUpFirestore(intent)
        setUpEventListener()
    }

    private fun setUpEventListener() {
        Previous.setOnClickListener {
            index--
            bindViews()
        }

        Next.setOnClickListener {
            index++
            bindViews()
        }

        Submit.setOnClickListener {
            Log.d("FINALQUIZ", questions.toString())
            val intent = Intent(this, ResultActivity::class.java)
            val json  = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)
        }
    }

    private fun setUpFirestore(intent: Intent) {
        var date = intent.getStringExtra("DATE")
        if (date!= null)  {
            firestore.collection("quizzes")
                .whereEqualTo("title",date)
                .get()
                .addOnSuccessListener {
                    if (it!= null &&!it.isEmpty) {
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindViews()
                    }
                }
        }
    }

    private fun bindViews() {
        Previous.visibility = View.GONE
        Submit.visibility = View.GONE
        Next.visibility = View.GONE
        if(index == 1){
            Next.visibility= View.VISIBLE
        }
        else if (index==questions!!.size){
            Submit.visibility= View.VISIBLE
            Previous.visibility= View.VISIBLE
        }
        else
        {
            Previous.visibility = View.VISIBLE
            Next.visibility = View.VISIBLE
        }
        val questions = questions!!["question$index"]
        questions?.let{
            description.text = it.description
            val optionAdapter = OptionAdapter(this, it)
            optionList.layoutManager = LinearLayoutManager(this) // Set the layout manager
            optionList.adapter = optionAdapter
            optionList.setHasFixedSize(true)
        }
    }

}


/*package com.example.project11.activitys.activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project11.R
import com.example.project11.activitys.activitys.adapter.OptionAdapter
import com.example.project11.activitys.activitys.models.Questions
import com.example.project11.activitys.activitys.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson


class QuestionActivity : AppCompatActivity() {
    lateinit var firestore: FirebaseFirestore
    lateinit var description: TextView
    lateinit var optionList: RecyclerView
    var layoutManager = LinearLayoutManager(this)
    var quizzes: MutableList<Quiz>? = null
    var questions: MutableMap<String, Questions>? = null
    var index = 1
    lateinit var Previous: Button
    lateinit var Submit: Button
    lateinit var Next: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        firestore = FirebaseFirestore.getInstance()
        layoutManager = LinearLayoutManager(this)// Correct layout resource ID
        optionList = findViewById(R.id.optionList)
        description = findViewById(R.id.description)
        Previous = findViewById(R.id.btnPrevious)
        Submit = findViewById(R.id.btnSubmit)
        Next = findViewById(R.id.btnNext)

        setUpFirestore()
        setUpEventListener()
    }

    private fun setUpEventListener() {
        Previous.setOnClickListener {
            index--
            bindViews()
        }

        Next.setOnClickListener {
            index++
            bindViews()
        }

        Submit.setOnClickListener {
            Log.d("FINALQUIZ", questions.toString())
            val intent = Intent(this, ResultActivity::class.java)
            val json  = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)
        }
    }

    private fun setUpFirestore() {
        var date =intent.getStringExtra("DATE")
        if (date !=null)  {
            firestore.collection("quizzes")
                .whereEqualTo("title",date)
                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty) {
                        quizzes = it.toObjects(Quiz::class.java)
                        questions=quizzes!![0].questions
                        bindViews()
                    }
                }
        }
    }

    private fun bindViews() {
        Previous.visibility = View.GONE
        Submit.visibility = View.GONE
        Next.visibility = View.GONE
        if(index == 1){
            Next.visibility= View.VISIBLE
        }
        else if (index==questions!!.size){
            Submit.visibility= View.VISIBLE
            Previous.visibility= View.VISIBLE
        }
        else
        {
            Previous.visibility = View.VISIBLE
            Next.visibility = View.VISIBLE
        }
        val questions = questions!!["question$index"]
        questions?.let{
            description.text = it.description
            val optionAdapter = OptionAdapter(this, it)
            optionList.layoutManager = LinearLayoutManager(this) // Set the layout manager
            optionList.adapter = optionAdapter
            optionList.setHasFixedSize(true)
        }
    }

}


 */