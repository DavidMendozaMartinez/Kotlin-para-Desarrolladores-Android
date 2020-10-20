package com.davidmendozamartinez.myplayer.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun RecyclerView.ViewHolder.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    itemView.context.toast(text, duration)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
        LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun ImageView.loadUrl(url: String) {
    Glide.with(this).load(url).into(this)
}

inline fun <reified T : Activity> Context.startActivity(vararg pairs: Pair<String, Any?>) {
    Intent(this, T::class.java)
            .apply { putExtras(bundleOf(*pairs)) }
            .also(::startActivity)
}