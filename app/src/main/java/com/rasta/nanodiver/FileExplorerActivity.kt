package com.rasta.nanodiver

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class FileExplorerActivity : AppCompatActivity() {

    private lateinit var layout: LinearLayout
    private var currentDir: File = File("/sdcard") // Default start

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        val scrollView = ScrollView(this).apply {
            addView(layout)
        }

        setContentView(scrollView)
        listFiles(currentDir)
    }

    private fun listFiles(dir: File) {
        layout.removeAllViews()

        val pathView = TextView(this).apply {
            text = "📂 Path: ${dir.absolutePath}\n"
        }
        layout.addView(pathView)

        if (dir.parentFile != null) {
            val backButton = Button(this).apply {
                text = "⬅️ Go Up"
                setOnClickListener { listFiles(dir.parentFile!!) }
            }
            layout.addView(backButton)
        }

        dir.listFiles()?.sortedBy { it.name }?.forEach { file ->
            val button = Button(this).apply {
                text = if (file.isDirectory) "📁 ${file.name}" else "📄 ${file.name}"
                setOnClickListener {
                    if (file.isDirectory) {
                        currentDir = file
                        listFiles(file)
                    } else {
                        openFile(file)
                    }
                }
            }
            layout.addView(button)
        } ?: run {
            layout.addView(TextView(this).apply {
                text = "❌ Access Denied or Empty"
            })
        }
    }

    private fun openFile(file: File) {
        try {
            val uri = Uri.fromFile(file)
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "*/*")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startActivity(Intent.createChooser(intent, "Open with"))
        } catch (e: Exception) {
            Toast.makeText(this, "Unable to open file", Toast.LENGTH_SHORT).show()
        }
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
