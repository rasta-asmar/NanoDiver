package com.example.nanodiver

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class FileExplorerActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private var currentPath: File = Environment.getExternalStorageDirectory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_explorer)

        listView = findViewById(R.id.fileListView)
        loadFiles(currentPath)

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedFile = listView.adapter.getItem(position) as File
            if (selectedFile.isDirectory) {
                currentPath = selectedFile
                loadFiles(currentPath)
            } else {
                openFile(selectedFile)
            }
        }
    }

    private fun loadFiles(path: File) {
        val files = path.listFiles()
        if (files != null) {
            val fileList = files.sortedWith(compareBy({ !it.isDirectory }, { it.name }))
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fileList)
            listView.adapter = adapter
        } else {
            Toast.makeText(this, "Cannot access directory", Toast.LENGTH_SHORT).show()
        }
        title = path.absolutePath
    }

    private fun openFile(file: File) {
        try {
            val uri: Uri = FileProvider.getUriForFile(
                this,
                "${applicationContext.packageName}.provider",
                file
            )
            val intent = Intent(Intent.ACTION_EDIT)
            intent.setDataAndType(uri, "*/*")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "No app available to edit this file", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to open file: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

fun styledButton(text: String, onClick: () -> Unit): Button {
    return Button(this).apply {
        this.text = text
        setPadding(20, 20, 20, 20)
        textSize = 16f
        setBackgroundColor(0xFF222222.toInt()) // Dark Gray
        setTextColor(0xFFFFFFFF.toInt())       // White
        setOnClickListener { onClick() }
    }
}

layout.addView(styledButton("📁 File Explorer") {
    startActivity(Intent(this@MainActivity, FileExplorerActivity::class.java))
})

val spacer = Space(this)
spacer.layoutParams = LinearLayout.LayoutParams(0, 40)
layout.addView(spacer)

