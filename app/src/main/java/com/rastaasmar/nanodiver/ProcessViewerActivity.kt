package com.example.nanodiver

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class ProcessViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process_viewer)
        
    import android.util.Log

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process_viewer)

        Log.d("NanoDiver", "ProcessViewerActivity started")
}


        val listView = findViewById<ListView>(R.id.processListView)
        val processList = getRunningProcesses()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, processList)
        listView.adapter = adapter
    }

    private fun getRunningProcesses(): List<String> {
        val processList = mutableListOf<String>()
        val procDir = File("/proc")

        if (procDir.exists() && procDir.isDirectory) {
            procDir.listFiles()?.forEach { file ->
                if (file.isDirectory && file.name.all { it.isDigit() }) {
                    val cmdlineFile = File(file, "cmdline")
                    val processName = try {
                        cmdlineFile.readText().trim().ifEmpty { "[kernel]" }
                    } catch (e: Exception) {
                        "[unknown]"
                    }
                    processList.add("${file.name}: $processName")
                }
            }
        }

        return processList.sorted()
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
