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

        } 
    }  
}   

package com.example.nanodiver

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openProcessViewerButton = findViewById<Button>(R.id.openProcessViewerButton)
        openProcessViewerButton.setOnClickListener {
            val intent = Intent(this, ProcessViewerActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.nanodiver

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private val STORAGE_PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkStoragePermission()

        val processBtn = findViewById<Button>(R.id.processViewerBtn)
        processBtn.setOnClickListener {
            val intent = Intent(this, ProcessViewerActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                STORAGE_PERMISSION_CODE
            )
        } else {
            Toast.makeText(this, "Storage permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
        val openFileExplorerButton = findViewById<Button>(R.id.openFileExplorerButton)
        openFileExplorerButton.setOnClickListener {
        val intent = Intent(this, FileExplorerActivity::class.java)
        startActivity(intent)
     }

        val btnConnections = Button(this).apply {
        text = "Connection Monitor"
        setOnClickListener {
        startActivity(Intent(this@MainActivity, ConnectionMonitorActivity::class.java))
    }
}
        layout.addView(btnConnections)

        val btnStorage = Button(this).apply {
        text = "Storage Analyzer"
        setOnClickListener {
        startActivity(Intent(this@MainActivity, StorageAnalyzerActivity::class.java))
    }
}
        layout.addView(btnStorage)

        val btnLogs = Button(this).apply {
        text = "System Logs"
        setOnClickListener {
        startActivity(Intent(this@MainActivity, LogViewerActivity::class.java))
    }
}
        layout.addView(btnLogs)

        val btnConnections = Button(this).apply {
        text = "Network Connections"
        setOnClickListener {
        startActivity(Intent(this@MainActivity, ConnectionTrackerActivity::class.java))
    }
}
        layout.addView(btnConnections)

        val btnFiles = Button(this).apply {
        text = "📁 File Explorer"
        setOnClickListener {
        startActivity(Intent(this@MainActivity, FileExplorerActivity::class.java))
    }
}
        layout.addView(btnFiles)




