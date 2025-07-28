package com.nanodiver

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ProcessesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_processes)

        val listView: ListView = findViewById(R.id.processListView)
        val processList = getRunningProcesses()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, processList)
        listView.adapter = adapter
    }

    private fun getRunningProcesses(): List<String> {
        val processInfo = mutableListOf<String>()
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val processes = manager.runningAppProcesses

        processes?.forEach {
            val info = "PID: ${it.pid}, Process Name: ${it.processName}"
            processInfo.add(info)
        }
        return processInfo
    }
}
