package com.instana.android.screens.credentials

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.instana.android.databinding.CredentialScreenBinding
import com.instana.android.screens.home.HomeScreen
import com.instana.android.shared.BaseActivity
import com.instana.android.utils.Constants
import com.instana.android.utils.MyPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CredentialScreen : BaseActivity<CredentialScreenBinding>() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        navigateToHome()
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        createBinding<CredentialScreenBinding> { inflater -> CredentialScreenBinding.inflate(inflater) }

        with(binding) {
            //HardCoding the Credentials here for the ease of developer to enter it manually.
            instanaKey.setText("ENTER YOUR KEY")
            reportingUrl.setText("ENTER THE REPORTING URL")

            saveRestart.setOnClickListener {
                validateKeyAndUrl(
                    key = instanaKey.text.toString(),
                    url = reportingUrl.text.toString()
                )

// Restart the app by launching the launcher activity after a delay of 2 seconds using coroutines
                CoroutineScope(Dispatchers.Main).launch {
                    delay(2000L)

                    val intent = packageManager.getLaunchIntentForPackage(packageName)
                    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    //TODO: move to ViewModel with Shared preference from DI
    /**
     * Store the Instana key and reporting URL to shared preferences.
     *
     * @param key The Instana key to be stored.
     * @param url The Instana reporting URL to be stored.
     */
    private fun storeKeyAndUrlToBuffer(key: String, url: String) {
        val preference = MyPreferences(context = applicationContext)
        preference.setValue(Constants.InstanaKey,key)
        preference.setValue(Constants.InstanaReportingUrl,url)
    }

    //TODO: move to ViewModel
    /**
     * Validate the Instana key and reporting URL.
     * If both key and URL are not empty, store them to shared preferences.
     *
     * @param key The Instana key to be validated and stored.
     * @param url The Instana reporting URL to be validated and stored.
     */
    private fun validateKeyAndUrl(key: String, url: String) {
        if(key.isNotEmpty()&&url.isNotEmpty()){
            storeKeyAndUrlToBuffer(key, url)
        }
    }

    //TODO:remove navigator
    /**
     * Navigate to the HomeScreen if the Instana key is already stored in shared preferences.
     */
    private fun navigateToHome(){
        if(MyPreferences(context = applicationContext).getValue(Constants.InstanaKey,"").toString().isNotEmpty()){
            startActivity(Intent(this,HomeScreen::class.java))
            finish()
        }
    }
}
