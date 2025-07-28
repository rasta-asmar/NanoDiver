package com.rasta.nanodiver

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class ConnectionMonitorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)
        setContentView(textView)

        val tcpConnections = parseConnections("/proc/net/tcp")
        val udpConnections = parseConnections("/proc/net/udp")

        val result = StringBuilder()
        result.append("Active TCP Connections:\n\n")
        result.append(tcpConnections.joinToString("\n"))
        result.append("\n\nActive UDP Connections:\n\n")
        result.append(udpConnections.joinToString("\n"))

        textView.text = result.toString()
    }

    private fun parseConnections(path: String): List<String> {
        val file = File(path)
        if (!file.exists()) return listOf("File not found: $path")

        val lines = file.readLines().drop(1) // skip header
        return lines.mapNotNull { line ->
            val parts = line.trim().split(Regex("\\s+"))
            if (parts.size > 2) {
                val local = hexToIpPort(parts[1])
                val remote = hexToIpPort(parts[2])
                "$local <-> $remote"
            } else null
        }
    }

    private fun hexToIpPort(hex: String): String {
        val (ipHex, portHex) = hex.split(":")
        val ip = ipHex.chunked(2).reversed().joinToString(".") { it.toInt(16).toString() }
        val port = portHex.toInt(16)
        return "$ip:$port"
    }
}
