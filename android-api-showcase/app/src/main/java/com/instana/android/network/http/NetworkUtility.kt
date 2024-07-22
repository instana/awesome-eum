package com.instana.android.network.http

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

typealias SuccessCallback = (response: String) -> Unit
typealias ErrorCallback = (exception: Exception) -> Unit
/**
 * Makes an HTTP GET request to the specified URL on a background thread.
 *
 * @param urlString The URL to make the request to.
 * @param onSuccess Callback function to be executed on successful response.
 * @param onError Callback function to be executed in case of an error.
 */
fun makeHttpGetRequest(urlString: String, onSuccess: SuccessCallback, onError: ErrorCallback) {
    Thread {
        try {
            // Create URL object from the given string
            val url = URL(urlString)

            // Open a connection to the URL as HttpURLConnection
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            // Get the HTTP response code
            val responseCode = connection.responseCode

            // If the response code is successful (HTTP_OK), read the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var line: String?

                // Read the response line by line and append to StringBuilder
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()

                // Execute the onSuccess callback with the response as a string
                onSuccess(response.toString())
            } else {
                // Throw an exception with the response code if the request failed
                throw Exception("HTTP request failed with response code: $responseCode")
            }

            // Disconnect the connection
            connection.disconnect()
        } catch (e: Exception) {
            // Execute the onError callback with the exception
            onError(e)
        }
    }.start()
}
