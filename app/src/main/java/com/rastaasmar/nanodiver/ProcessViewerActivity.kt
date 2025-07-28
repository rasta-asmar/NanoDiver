package com.rastaasmar.nanodiver

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.InputStreamReader

class ProcessViewerActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProcessAdapter
    private val processList = mutableListOf<ProcessInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process_viewer)

        recyclerView = findViewById(R.id.processRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProcessAdapter(processList)
        recyclerView.adapter = adapter

        fetchProcesses()
    }

    private fun fetchProcesses() {
        try {
            val process = Runtime.getRuntime().exec("ps")
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String?

            // Skip header line
            reader.readLine()

            while (reader.readLine().also { line = it } != null) {
                line?.let {
                    val tokens = it.trim().split("\\s+".toRegex())
                    if (tokens.size >= 9) {
                        val pid = tokens[1]
                        val user = tokens[0]
                        val name = tokens.last()
                        processList.add(ProcessInfo(pid, user, name))
                    }
                }
            }

            adapter.notifyDataSetChanged()
        } catch (e: Exception) {
            Toast.makeText(this, "Error reading processes: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
