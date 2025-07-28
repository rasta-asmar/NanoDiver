package com.rastasmar.nanodiver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        welcomeText.text = "Welcome to NanoDiver 🧠"
    }
}

fun styledButton(text: String, onClick: () -> Unit): Button {
    return Button(this).apply {
        this.text = text
        setPadding(20, 20, 20, 20)
        textSize = 16f
        setBackgroundColor(0xFF222222.toInt()) // Dark Gray
        setTextColor(0xFFFFFFFF.toInt())       // White
        setOnClickListener { onClick() }
    }
}

layout.addView(styledButton("📁 File Explorer") {
    startActivity(Intent(this@MainActivity, FileExplorerActivity::class.java))
})

val spacer = Space(this)
spacer.layoutParams = LinearLayout.LayoutParams(0, 40)
layout.addView(spacer)
