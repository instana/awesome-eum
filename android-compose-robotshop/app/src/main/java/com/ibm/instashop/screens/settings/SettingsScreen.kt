package com.ibm.instashop.screens.settings

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.ibm.instashop.R
import com.ibm.instashop.common.Constants.CurrentVersionInUse
import com.ibm.instashop.common.Constants.VERSION_NUMBER
import com.ibm.instashop.common.Constants.VERSION_NUMBER_V2
import com.ibm.instashop.common.utils.efffects.getAppVersion
import com.ibm.instashop.screens.common.components.CustomInputField
import com.ibm.instashop.screens.common.components.PrimaryButton

@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val v1v2Text = remember {
        mutableStateOf(CurrentVersionInUse)
    }

    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        ExitAlertDialog(
            onDismiss = { showDialog = false },
            onConfirm = { exitApp(context as Activity) }
        )
    }
    ConstraintLayout {
        var url by remember { mutableStateOf(TextFieldValue("")) }
        var key by remember { mutableStateOf(TextFieldValue("")) }
        val (instanaKey, instanaUrl) = settingsViewModel.getKeyUrl()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Top
        ) {
            InfoTable(context,settingsViewModel, instanaKey = instanaKey, instanaUrl = instanaUrl)
            CustomInputField(
                placeholder = "Place Your Reporting URL",
                trailingIcon = R.drawable.card_number,
                label = "Reporting URL",
                keyboardType = KeyboardType.Email,
                visualTransformation = VisualTransformation.None,
                onChanged = { newUrl ->
                    url = newUrl
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomInputField(
                placeholder = "Key",
                trailingIcon = R.drawable.password,
                label = "Key",
                keyboardType = KeyboardType.Email,
                visualTransformation = VisualTransformation.None,
                onChanged = { newKey ->
                    key = newKey
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            PrimaryButton(shapeSize = 15f, btnText = "Update") {
                settingsViewModel.updateKeyUrl(key.text, url.text)
                showDialog = true
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Current Calls to ${v1v2Text.value} backend")
                Switch(checked = CurrentVersionInUse == VERSION_NUMBER, onCheckedChange = {
                    if (!it) {
                        v1v2Text.value = VERSION_NUMBER_V2
                        CurrentVersionInUse = VERSION_NUMBER_V2
                        settingsViewModel.updateCurrentVersion(CurrentVersionInUse)
                    } else {
                        v1v2Text.value = VERSION_NUMBER
                        CurrentVersionInUse = VERSION_NUMBER
                        settingsViewModel.updateCurrentVersion(CurrentVersionInUse)
                    }
                })
            }


        }
    }
}

@Composable
fun InfoTable(
    context: Context,
    settingsViewModel: SettingsViewModel,
    instanaUrl: String,
    instanaKey: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        InfoRow(label = "Current App Version", value = getAppVersion(context))
        Spacer(modifier = Modifier.height(15.dp))
        InfoRow(label = "Current User Name", value = settingsViewModel.getUserName())
        InfoRow(label = "Current User Email", value = settingsViewModel.getUserEmail())
        InfoRow(label = "Current User ID", value = settingsViewModel.getUserID())
        Spacer(modifier = Modifier.height(15.dp))
        InfoRow(label = "Current Instana URL", value = instanaUrl)
        Spacer(modifier = Modifier.height(15.dp))
        InfoRow(label = "Current Instana Key", value = instanaKey)
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun InfoRow(label: String, value: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label :",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value ?: "",
            style = MaterialTheme.typography.body2,
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
fun ExitAlertDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                onConfirm()
            }) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss }) {
                Text("No")
            }
        },
        title = { Text("Exit App") },
        text = { Text("Are you sure you want to exit the app?") }
    )
}

fun exitApp(activity: Activity) {
    activity.finishAffinity()
    System.exit(0)
}