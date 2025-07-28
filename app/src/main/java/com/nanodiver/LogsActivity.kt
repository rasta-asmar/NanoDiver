package com.nanodiver

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class LogsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs)

        val logsTextView = findViewById<TextView>(R.id.logsTextView)
        logsTextView.text = getLogs()
    }

    private fun getLogs(): String {
        val log = StringBuilder()
        try {
            val process = Runtime.getRuntime().exec("logcat -d")
            val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                log.append(line).append("\n")
                line = bufferedReader.readLine()
            }
        } catch (e: Exception) {
            log.append("Error reading logs: ${e.message}")
        }
        return log.toString()
    }
}
