package com.rasta.nanodiver

import android.os.Bundle
import android.os.Environment
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.text.DecimalFormat

class StorageAnalyzerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scrollView = ScrollView(this)
        val textView = TextView(this)
        scrollView.addView(textView)
        setContentView(scrollView)

        val rootDirs = listOf(
            Environment.getExternalStorageDirectory(), // /storage/emulated/0
            File("/sdcard/"),
            File("/data/"),
            File("/storage/")
        ).filter { it.exists() && it.canRead() }

        val result = StringBuilder()
        rootDirs.forEach { dir ->
            result.append("🔍 Scanning: ${dir.path}\n")
            val usage = getDirSize(dir)
            result.append("📦 ${formatSize(usage)} used\n\n")
        }

        textView.text = result.toString()
    }

    private fun getDirSize(dir: File): Long {
        if (!dir.exists() || !dir.canRead()) return 0
        if (dir.isFile) return dir.length()

        var total = 0L
        dir.listFiles()?.forEach { file ->
            total += getDirSize(file)
        }
        return total
    }

    private fun formatSize(bytes: Long): String {
        val df = DecimalFormat("#.##")
        val kb = 1024.0
        val mb = kb * 1024
        val gb = mb * 1024

        return when {
            bytes >= gb -> "${df.format(bytes / gb)} GB"
            bytes >= mb -> "${df.format(bytes / mb)} MB"
            bytes >= kb -> "${df.format(bytes / kb)} KB"
            else -> "$bytes B"
        }
    }
}
