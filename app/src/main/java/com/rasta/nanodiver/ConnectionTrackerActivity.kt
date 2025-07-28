package com.rasta.nanodiver

import android.os.Bundle
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class ConnectionTrackerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scrollView = ScrollView(this)
        val textView = TextView(this)
        scrollView.addView(textView)
        setContentView(scrollView)

        val output = StringBuilder()

        output.appendLine("📡 Active Connections:\n")

        listOf("tcp", "tcp6", "udp", "udp6").forEach { proto ->
            output.appendLine("=== /proc/net/$proto ===")
            val file = File("/proc/net/$proto")
            if (file.exists()) {
                file.forEachLine { line -> output.appendLine(line) }
            } else {
                output.appendLine("Not available")
            }
            output.appendLine("\n")
        }

        textView.text = output.toString()
    }
}
