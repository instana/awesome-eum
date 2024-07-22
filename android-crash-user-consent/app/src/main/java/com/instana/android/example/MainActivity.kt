package com.instana.android.example

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.instana.android.Instana
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class MainActivity : AppCompatActivity() {


    companion object {
        val FLAG = "isFlagState"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.execute_request)
        val responseTextView = findViewById<TextView>(R.id.response)
        val generateCrashButton: Button = findViewById(R.id.btn_generate_crash)
        val sharedPreferences = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        //val FLAG = "isFlagState"

//        Get the current state of "isFlagState" from SharedPreferences
        val isFlagState = sharedPreferences.getString(FLAG, "NOT_SET")

        when (isFlagState) {
            "NOT_SET" -> {
                // If it's the first launch, show the alert box
                AlertDialog.Builder(this).apply {
                    setTitle("Instana Agent Test")
                    setMessage("Would you allow app crash logs sent to the server?")
                    setPositiveButton("Yes") { dialog, which ->
                        Toast.makeText(applicationContext, "Clicked Yes", Toast.LENGTH_LONG).show()
                        sharedPreferences.edit().apply {
                            putString(FLAG, "YES")
                        }.apply()
                        showExecuterequestButton()
                        generateCrashButton.isEnabled = true
                    }
                    setNegativeButton("No") { dialog, which ->
                        Toast.makeText(applicationContext, "Clicked No", Toast.LENGTH_LONG).show()
                        sharedPreferences.edit().apply {
                            putString(FLAG, "NO")
                        }.apply()
                        generateCrashButton.isEnabled = false
                    }
                    create().show()
                }
            }

            "YES" -> {
                // If it's already set to "YES", show the appropriate message
                Toast.makeText(applicationContext, "All logs are sent to the server", Toast.LENGTH_LONG).show()
                generateCrashButton.isEnabled = true
            }

            "NO" -> {
                // If it's already set to "NO", show the appropriate message
                Toast.makeText(applicationContext, "Logs are not sent to the server", Toast.LENGTH_LONG).show()
                generateCrashButton.isEnabled = false
            }
        }


        button.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {
                val result = executeQuery()
                responseTextView.text = result
            }
        }

        generateCrashButton.setOnClickListener {
            generateCrash()
        }
    }


    @SuppressLint("Range")
    override fun onResume() {
        super.onResume()

        Instana.view = "Main screen"
    }

    private fun showExecuterequestButton() {
        AlertDialog.Builder(this).apply {
            setTitle("Instana Agent Test")
            setMessage("Press OK button to crash running test app.")
            setPositiveButton("OK") { dialog, which ->
                Toast.makeText(applicationContext, "Clicked OK", Toast.LENGTH_LONG).show()
            }
            setNegativeButton("No") { dialog, which ->
                Toast.makeText(applicationContext, "Clicked No", Toast.LENGTH_LONG).show()
            }
            create().show()
        }
    }

    private fun generateCrash() {
        val result = 10 / 0
        true
    }

    private suspend fun executeQuery(): String? = withContext(Dispatchers.IO) {
        val okHttpClient = OkHttpClient.Builder().build()
        val okHttpRequest = Request.Builder()
            .url("http://35.155.175.252:8080/")
            .get()
            .build()
        return@withContext try {
            val response = okHttpClient.newCall(okHttpRequest).execute()
            response.body?.string()
        } catch (e: IOException) {
            e.message
        }
    }
}
