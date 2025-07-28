package com.rastaasmar.nanodiver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProcessAdapter(private val processList: List<ProcessInfo>) :
    RecyclerView.Adapter<ProcessAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pidText: TextView = view.findViewById(R.id.pidText)
        val userText: TextView = view.findViewById(R.id.userText)
        val nameText: TextView = view.findViewById(R.id.nameText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_process, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val process = processList[position]
        holder.pidText.text = "PID: ${process.pid}"
        holder.userText.text = "User: ${process.user}"
        holder.nameText.text = "Name: ${process.name}"
    }

    override fun getItemCount(): Int = processList.size
}
