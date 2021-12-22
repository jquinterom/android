package com.example.volleyapirest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup
        setup()
    }


    fun setup(){

        val textView = findViewById<TextView>(R.id.textId)
        val url = "https://jsonplaceholder.typicode.com/posts/1"

        /*
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
                textView.text = "Response is: ${response.substring(0, 500)}"
            },
            { error ->
                    textView.text = "That didn't work!"
                    error.printStackTrace()
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
         */

        /*
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->

                for(i in 0 until response.length()){
                    val obj = response.getJSONObject(i)
                    Log.d(i.toString(), obj.getString("title"));
                }

                textView.text = "Records found";
            },
            { error ->
                // TODO: Handle error
                error.printStackTrace()
                textView.text = "That didn't work!"
            }
        )

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest)
         */


        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                textView.text = "Record found";
                Log.d("Title Json", response.getString("title"))
                Log.d("Body json", response.getString("body"))
                Log.d("ID json", response.getInt("id").toString())
            },
            { error ->
                // TODO: Handle error
                error.printStackTrace()
            }
        )

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}