package com.example.volleyapirest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.*

class MainActivity : AppCompatActivity() {

    // Variables de la actividad
    private val url = "https://jsonplaceholder.typicode.com/posts/"
    private var textView: TextView? = null
    private val idPost = "1";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup
        setup()
    }


    /**
     * LLamaremos a las distintas funciones para las peticiones al API
     * */
    private fun setup(){
        // Inicializamos TextView
        textView = findViewById(R.id.textId)

        // StringRequest
        stringRequest()

        // JsonObjectRequest
        jsonObjectRequest()

        // JsonArrayRequest
        jsonArrayRequest()
    }

    /**
     * Función para realizar petición tipo string
     * */
    @SuppressLint("SetTextI18n")
    private fun stringRequest(){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
                textView?.text = "API connection success"
                Log.d("responseString", response)
            },
            { error ->
                textView?.text = "That didn't work!"
                error.printStackTrace()
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    /**
     * Función para realizar petición tipo objeto JSON
     * */
    @SuppressLint("SetTextI18n")
    private fun jsonObjectRequest(){
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url + idPost, null,
            { response ->
                textView?.text = "Record found";
                Log.d("Title Json", response.getString("title"))
                Log.d("Body json", response.getString("body"))
                Log.d("ID json", response.getInt("id").toString())
            },
            { error ->
                // TODO: Handle error
                error.printStackTrace()
                textView?.text = "That didn't work!"
            }
        )

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    /**
     * Función para realizar petición de tipo arreglo JSON
     * */
    @SuppressLint("SetTextI18n")
    private fun jsonArrayRequest(){
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->

                for(i in 0 until response.length()){
                    val obj = response.getJSONObject(i)
                    Log.d(i.toString(), obj.getString("title"));
                }

                textView?.text = "Records found";
            },
            { error ->
                // TODO: Handle error
                error.printStackTrace()
                textView?.text = "That didn't work!"
            }
        )

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest)
    }
}