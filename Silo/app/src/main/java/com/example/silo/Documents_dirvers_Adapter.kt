package com.example.silo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat.startActivity




class Documents_dirvers_Adapter (private val mDocuments: List<Document>) : RecyclerView.Adapter<Documents_dirvers_Adapter.ViewHolder>(){

    private var context : Context? = null;
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val context = parent.context
        context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val documentView = inflater.inflate(R.layout.document_driver, parent, false)
        // Return a new holder instance

        return ViewHolder(documentView)
    }

    override fun onBindViewHolder(holder: Documents_dirvers_Adapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val document: Document = mDocuments[position]
        val txtName = holder.txtName
        val imageView  = holder.imageView

        // Set item views based on your views and data model
        txtName.text = document.name

        imageView.setOnClickListener {

            startActivity(
                context!!,
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://10.0.2.2:8000/pdf/documents_drivers/" + document.name)
                ), null
            )
        }

    }

    override fun getItemCount(): Int {
        return mDocuments.size
    }


}