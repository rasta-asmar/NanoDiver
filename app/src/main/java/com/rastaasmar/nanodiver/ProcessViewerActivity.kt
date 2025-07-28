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
