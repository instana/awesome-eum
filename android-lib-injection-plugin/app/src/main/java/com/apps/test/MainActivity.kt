package com.apps.test

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()
    private val apiUrl = "https://jsonplaceholder.typicode.com/todos/1" // Sample API URL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindContent()
    }

    private suspend fun makeApiCall() {
        val request = Request.Builder()
            .url(apiUrl)
            .build()

        try {
            // Execute the request asynchronously in the IO dispatcher
            val response: Response = client.newCall(request).execute()

            if (response.isSuccessful) {
                val responseData = response.body?.string()
                Log.d("MainActivity", "Response: $responseData")

                // Update the UI on the main thread if needed
                withContext(Dispatchers.Main) {
                    // Update UI, e.g., show response data in a TextView
                }
            } else {
                Log.e("MainActivity", "Request failed with code: ${response.code}")
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "API call failed: ${e.message}")
        }
    }

    private fun bindContent() {
        val button = findViewById<Button>(R.id.makeCall)
        button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                makeApiCall()
            }
        }
    }
}
