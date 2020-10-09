package com.davidmendozamartinez.myplayer

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun RecyclerView.ViewHolder.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    itemView.context.toast(text, duration)
}