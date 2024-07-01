package com.example.project11.activitys.activitys

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project11.R
import com.example.project11.activitys.activitys.models.Quiz
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {
    lateinit var quiz: Quiz
    lateinit var txtAnswer: TextView
    lateinit var txtScore: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        txtAnswer = findViewById<TextView>(R.id.txtAnswer)
        txtScore = findViewById<TextView>(R.id.txtScore)
        setUpViews()
    }

    private fun setUpViews() {
        val quizData = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<Quiz>(quizData, Quiz::class.java)
        calculateScore()
        setAnswerView()
    }

    private fun calculateScore() {
        var score = 0
        for (entry in quiz.questions.entries) {
            val question = entry.value
            if (question.answer == question.userAnswer) {
                score += 10
            }
        }
        txtScore.text = "Your Score : $score"
    }

    private fun setAnswerView() {
        val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color='#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT)
        } else {
            txtAnswer.text = Html.fromHtml(builder.toString())
        }
    }
}




/*package com.example.project11.activitys.activitys

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project11.R
import com.example.project11.activitys.activitys.models.Quiz
import com.google.gson.Gson


class ResultActivity : AppCompatActivity() {
    lateinit var quiz: Quiz
    var txtAnswer = findViewById<TextView>(R.id.txtAnswer)
    var txtScore = findViewById<TextView>(R.id.txtScore)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpViews()
    }

    private fun setUpViews() {
        var quizData = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<Quiz>(quizData, Quiz::class.java)
        calculateScore()
        setAnswerView()
    }
    private fun calculateScore() {
        var score = 0
        for (entry in quiz.questions.entries) {
            var question = entry.value
            if (question.answer == question.userAnswer) {
                score += 10
            }
        }
        txtScore.text = "Your Score : $score"
    }

    private fun setAnswerView() {
        var builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            var question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.N) {
            txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            txtAnswer.text = Html.fromHtml(builder.toString());
        }
    }

}
*/