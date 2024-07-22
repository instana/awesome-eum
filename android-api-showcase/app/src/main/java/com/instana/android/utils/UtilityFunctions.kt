package com.instana.android.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.widget.Button
import android.widget.LinearLayout
import com.google.android.material.textfield.TextInputEditText
import com.instana.android.core.SuspendReportingType

/**
 * Shows an input dialog with a text input field and a button.
 * @param buttonMessage The message to be displayed on the button.
 * @param context The context in which the dialog is shown.
 * @param userInput The callback function to handle the user input.
 */
private lateinit var dialog: AlertDialog

fun showInputDialog(buttonMessage: String, context: Context, userInput: (String) -> Unit) {
    val dialogBuilder = AlertDialog.Builder(context)
    val layout = LinearLayout(context)
    val editText = TextInputEditText(context)
    val button = Button(context)

    // Set up the LinearLayout
    layout.orientation = LinearLayout.VERTICAL
    layout.addView(editText)
    layout.addView(button)

    // Set up the AlertDialog
    dialogBuilder.setView(layout)
    dialogBuilder.setTitle("Input Dialog")

    // Set up the Button
    button.text = buttonMessage
    button.setOnClickListener {
        val inputString = editText.text.toString()
        userInput.invoke(inputString)
        // Dismiss the dialog
        dialog.dismiss()
    }

    // Create and show the dialog
    dialog = dialogBuilder.create()
    dialog.show()
}

/**
 * Simulates an Application Not Responding (ANR) error by performing a long-running operation on the main thread.
 */
fun simulateANR() {
    val mainHandler = Handler(Looper.getMainLooper())
    mainHandler.postDelayed({
        // Perform a long-running operation on the main thread
        while (true) {
            // Simulating a CPU-intensive operation
        }
    }, 5000) // Delay before starting the long-running operation (5 seconds)
}

/**
 * Shows an info dialog with the given title and message.
 * @param title The title of the dialog.
 * @param message The message to be displayed in the dialog.
 * @param context The context in which the dialog is shown.
 */
fun showInfoDialog(title: String, message: String, context: Context) {
    val dialogBuilder = AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        .setCancelable(false)

    val dialog = dialogBuilder.create()
    dialog.show()
}

/**
 * Converts a string to its corresponding SuspendReportingType enum value.
 * @return The SuspendReportingType enum value.
 */
fun String.getSuspensionType(): SuspendReportingType {
    return when (this) {
        SuspendReportingType.LOW_BATTERY_OR_CELLULAR_CONNECTION.toString() -> SuspendReportingType.LOW_BATTERY_OR_CELLULAR_CONNECTION
        SuspendReportingType.NEVER.toString() -> SuspendReportingType.NEVER
        SuspendReportingType.CELLULAR_CONNECTION.toString() -> SuspendReportingType.CELLULAR_CONNECTION
        else -> SuspendReportingType.LOW_BATTERY
    }
}

/**
 * Converts an Editable to a Long value.
 * @return The Long value.
 */
fun Editable.toLong(): Long {
    return this.toString().toLong()
}
