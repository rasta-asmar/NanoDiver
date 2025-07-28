package com.nanodiver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("Welcome to NanoDiver")
        }
    }
}
package com.nanodiver

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogs = findViewById<Button>(R.id.btn_logs)
        val btnProcesses = findViewById<Button>(R.id.btn_processes)
        val btnFiles = findViewById<Button>(R.id.btn_files)
        val btnNetwork = findViewById<Button>(R.id.btn_network)

        btnLogs.setOnClickListener {
            Toast.makeText(this, "Accessing logs...", Toast.LENGTH_SHORT).show()
            // TODO: Add real log access logic
        }

        btnProcesses.setOnClickListener {
            Toast.makeText(this, "Listing processes...", Toast.LENGTH_SHORT).show()
            // TODO: Add process list logic
        }

        btnFiles.setOnClickListener {
            Toast.makeText(this, "Opening file explorer...", Toast.LENGTH_SHORT).show()
            // TODO: Add file explorer logic
        }

        btnNetwork.setOnClickListener {
            Toast.makeText(this, "Monitoring network...", Toast.LENGTH_SHORT).show()
            // TODO: Add network monitoring logic
        }
    }
}

         btnLogs.setOnClickListener {
             val intent = Intent(this, LogsActivity::class.java)
             startActivity(intent)
             val btnProcesses = findViewById<Button>(R.id.btnProcesses)
             btnProcesses.setOnClickListener {
             val intent = Intent(this, ProcessesActivity::class.java)
             startActivity(intent)
             val btnNetwork = findViewById<Button>(R.id.btnNetwork)
             btnNetwork.setOnClickListener {
             val intent = Intent(this, NetworkActivity::class.java)
             startActivity(intent)
             val btnFileExplorer = findViewById<Button>(R.id.btnFileExplorer)
             btnFileExplorer.setOnClickListener {
             val intent = Intent(this, FileExplorerActivity::class.java)
             startActivity(intent)
             val btnLogMonitor = findViewById<Button>(R.id.btnLogMonitor)
             btnLogMonitor.setOnClickListener {
             val intent = Intent(this, LogMonitorActivity::class.java)
             startActivity(intent)
}

}

        } 
    }  
}   
