package com.example.project11.activitys.activitys.models

data class Quiz(
    var id : String = "",
    var title: String = "",
    var questions: MutableMap<String, Questions> = mutableMapOf()
)
