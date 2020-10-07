package com.davidmendozamartinez.myplayer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val message = findViewById<EditText>(R.id.message)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener { toast("Hello ${message.text}") }
    }

    private fun toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, duration).show()
    }
}