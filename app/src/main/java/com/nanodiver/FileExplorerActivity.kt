package com.nanodiver

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FileExplorerActivity : AppCompatActivity() {

    private val PICK_FILE_REQUEST_CODE = 1001
    private lateinit var selectedUriTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_explorer)

        selectedUriTextView = findViewById(R.id.uriTextView)
        val openFileButton: Button = findViewById(R.id.btnOpenFile)

        openFileButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "*/*"
            }
            startActivityForResult(intent, PICK_FILE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            resultData?.data?.also { uri ->
                selectedUriTextView.text = "📂 File URI:\n$uri"

                // Open externally if wanted
                val externalIntent = Intent(Intent.ACTION_VIEW).apply {
                    setDataAndType(uri, contentResolver.getType(uri))
                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                }
                try {
                    startActivity(externalIntent)
                } catch (e: Exception) {
                    selectedUriTextView.append("\n\n⚠️ No app can handle this file.")
                }
            }
        }
    }
}
