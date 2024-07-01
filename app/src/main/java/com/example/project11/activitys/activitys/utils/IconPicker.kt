package com.example.project11.activitys.activitys.utils

import com.example.project11.R

object IconPicker {
    val icons = arrayOf(
        R.drawable.ic_icon1,
        R.drawable.___2_,
        R.drawable.___3_,
        R.drawable.___4_,
        R.drawable.___5_,
        R.drawable.___6_,
        R.drawable.___7_,
    )
    var currentIcon = 0

    fun getIcon(): Int {
        currentIcon = (currentIcon + 1) % icons.size
        return icons[currentIcon]
    }
}