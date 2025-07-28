package com.nanodiver

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.TrafficStats
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NetworkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        val textView: TextView = findViewById(R.id.networkTextView)
        val info = StringBuilder()

        info.appendLine("📡 Active Network:")
        info.appendLine(getNetworkInfo())

        info.appendLine("\n📊 Data Usage (App):")
        info.appendLine("Rx: ${TrafficStats.getUidRxBytes(android.os.Process.myUid())} bytes")
        info.appendLine("Tx: ${TrafficStats.getUidTxBytes(android.os.Process.myUid())} bytes")

        info.appendLine("\n🌐 Interfaces (system-wide):")
        info.appendLine("Rx Total: ${TrafficStats.getTotalRxBytes()} bytes")
        info.appendLine("Tx Total: ${TrafficStats.getTotalTxBytes()} bytes")

        textView.text = info.toString()
    }

    private fun getNetworkInfo(): String {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return "No active network"
        val capabilities = cm.getNetworkCapabilities(network) ?: return "No capabilities"

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "Wi-Fi"
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "Cellular"
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> "Ethernet"
            else -> "Unknown"
        }
    }
}
