package com.example.project11.activitys.activitys.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.project11.R
import com.example.project11.R.layout.quiz_item
import com.example.project11.activitys.activitys.QuestionActivity
import com.example.project11.activitys.activitys.models.Quiz
import com.example.project11.activitys.activitys.utils.ColorPicker
import com.example.project11.activitys.activitys.utils.IconPicker


class QuizAdapter (val context: Context, private val quizzes: List<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(quiz_item, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text = quizzes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.iconView.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener{
            Toast.makeText(context,quizzes[position].title,Toast.LENGTH_SHORT).show()
            val intent =Intent(context,QuestionActivity::class.java)
            intent.putExtra("DATE",quizzes[position].title)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.quizTitle)
        var iconView: ImageView = itemView.findViewById(R.id.quizIcon)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
    }
}











