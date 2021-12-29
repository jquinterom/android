package com.example.silo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Documents_dirvers_Adapter (private val mDocuments: List<Document>) : RecyclerView.Adapter<Documents_dirvers_Adapter.ViewHolder>(){

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        //val messageButton = itemView.findViewById<Button>(R.id.message_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val documentView = inflater.inflate(R.layout.document_driver, parent, false)
        // Return a new holder instance

        Log.d("size", itemCount.toString())
        return ViewHolder(documentView)
    }

    override fun onBindViewHolder(holder: Documents_dirvers_Adapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val document: Document = mDocuments[position]
        val txtName = holder.txtName

        // Set item views based on your views and data model
        txtName.text = document.name
    }

    override fun getItemCount(): Int {
        return mDocuments.size
    }


}