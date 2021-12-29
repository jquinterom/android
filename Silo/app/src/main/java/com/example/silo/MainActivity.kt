package com.example.silo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.example.silo.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setup()
    }


    private fun setup(){

        val url = "http://10.0.2.2:8000/api/documents"

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null, {
                val rvDocuments = findViewById<View>(R.id.rvDocuments) as RecyclerView
                val jsDocuments: JSONArray = it.getJSONArray("data");
                val listDocuments = mutableListOf<Document>()
                for(i in 0 until jsDocuments.length()) {
                    val jsDocument = jsDocuments.getJSONObject(i)
                    val document =  Document(jsDocument.getString("name"), jsDocument.getString("url"))
                    listDocuments.add(document)
                }

                val adapter = Documents_dirvers_Adapter(listDocuments)
                rvDocuments.adapter = adapter
                rvDocuments.layoutManager = LinearLayoutManager(this)

            }, {
                Log.e("error", it.toString())
            }
        )

        SingletonHttp.getInstance(this.applicationContext).addToRequestQueue(jsonRequest)
    }
}