package com.rasta.nanodiver

import android.os.Bundle
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class LogViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scrollView = ScrollView(this)
        val textView = TextView(this)
        scrollView.addView(textView)
        setContentView(scrollView)

        val logs = StringBuilder()

        try {
            val process = Runtime.getRuntime().exec("logcat -d")
            val reader = BufferedReader(InputStreamReader(process.inputStream))

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                logs.appendLine(line)
            }

            reader.close()
        } catch (e: Exception) {
            logs.append("❌ Error reading logs: ${e.message}")
        }

        textView.text = logs.toString()
    }
}
