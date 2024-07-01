package com.example.project11.activitys.activitys.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project11.R
import com.example.project11.activitys.activitys.QuestionActivity
import com.example.project11.activitys.activitys.adapter.OptionAdapter.OptionViewHolder
import com.example.project11.activitys.activitys.models.Questions

class OptionAdapter(val context: QuestionActivity, val question: Questions) :
    RecyclerView.Adapter<OptionViewHolder>() {

    private var options: List<String> = listOf(question.option1, question.option2, question.option3, question.option4)

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var optionView = itemView.findViewById<TextView>(R.id.quiz_option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item, parent, false)
        return  OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionView.text = options[position]
        holder.itemView.setOnClickListener {
            question.userAnswer = options[position]
            notifyDataSetChanged()
        }
        if(question.userAnswer == options[position]){
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)

        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)

        }

    }

    override fun getItemCount(): Int {
        return options.size
    }
}