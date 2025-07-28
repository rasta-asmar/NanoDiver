package com.nanodiver

import android.os.Bundle
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class LogMonitorActivity : AppCompatActivity() {

    private lateinit var logOutput: TextView
    private lateinit var scrollView: ScrollView
    private lateinit var btnRefresh: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_monitor)

        logOutput = findViewById(R.id.txtLog)
        scrollView = findViewById(R.id.scrollView)
        btnRefresh = findViewById(R.id.btnRefresh)

        btnRefresh.setOnClickListener {
            readLogs()
        }

        readLogs()
    }

    private fun readLogs() {
        try {
            val process = Runtime.getRuntime().exec("logcat -d -t 100")
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val log = StringBuilder()
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                if (line!!.contains("NanoDiver") || line!!.contains("System") || line!!.contains("Security")) {
                    log.append(line).append("\n")
                }
            }

            logOutput.text = log.toString()

            scrollView.post {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN)
            }

        } catch (e: Exception) {
            logOutput.text = "⚠️ Failed to read logs:\n${e.message}"
        }
    }
}
