package com.instana.android.screens.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.instana.android.Instana
import com.instana.android.databinding.HomeScreenBinding
import com.instana.android.shared.BaseActivity
import com.instana.android.shared.GenericFragmentHolderActivity
import com.instana.android.utils.Constants
import com.instana.android.utils.MyPreferences

@SuppressLint("Range", "SetTextI18n")
class HomeScreen:BaseActivity<HomeScreenBinding>() {

    private lateinit var sharedPreferences: MyPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize shared preferences
        sharedPreferences = MyPreferences(applicationContext)

        // Set the view name for Instana monitoring
        Instana.view = Constants.OPTIONS_LISTING_SCREEN

        // Inflate the layout using view binding
        createBinding<HomeScreenBinding> { inflater -> HomeScreenBinding.inflate(inflater) }

        with(binding){
            // Set the displayed token key and reporting URL from shared preferences
            tokenKey.text = "Token Key : ${sharedPreferences.getValue(Constants.InstanaKey,"").toString()}"
            reportingUrl.text = "Reporting Url : ${sharedPreferences.getValue(Constants.InstanaReportingUrl,"").toString()}"

            createProfileDataBtn.setOnClickListener {
                startActivityWithOptions(Constants.CREATE_PROFILE)
            }
            createCrashBtn.setOnClickListener {
                startActivityWithOptions(Constants.GENERATE_CRASH)
            }
            createCustomEventBtn.setOnClickListener {
                startActivityWithOptions(Constants.CUSTOM_EVENTS)
            }
            makeNetworkCallBtn.setOnClickListener {
                startActivityWithOptions(Constants.NETWORK_OPERATION)
            }
            addView.setOnClickListener {
                startActivityWithOptions(Constants.MULTI_SCREEN_VIEW)
            }
            updateConfigBtn.setOnClickListener {
                startActivityWithOptions(Constants.VIEW_UPDATE_CONFIG)
            }
            createLogsBtn.setOnClickListener {
                startActivityWithOptions(Constants.CUSTOM_LOGS_SCREEN)
            }
        }
    }

    private fun startActivityWithOptions(optionSelected: String){
        val intent = Intent(this,GenericFragmentHolderActivity::class.java)

        // Set the appropriate screen ID based on the selected option
        when(optionSelected){
            Constants.CREATE_PROFILE -> {
                intent.putExtra(Constants.SCREEN_ID,Constants.CREATE_PROFILE)
            }
            Constants.GENERATE_CRASH -> {
                intent.putExtra(Constants.SCREEN_ID,Constants.GENERATE_CRASH)
            }
            Constants.MULTI_SCREEN_VIEW -> {
                intent.putExtra(Constants.SCREEN_ID,Constants.MULTI_SCREEN_VIEW)
            }
            Constants.NETWORK_OPERATION -> {
                intent.putExtra(Constants.SCREEN_ID,Constants.NETWORK_OPERATION)
            }
            Constants.CUSTOM_EVENTS -> {
                intent.putExtra(Constants.SCREEN_ID,Constants.CUSTOM_EVENTS)
            }
            Constants.VIEW_UPDATE_CONFIG -> {
                intent.putExtra(Constants.SCREEN_ID,Constants.VIEW_UPDATE_CONFIG)
            }
            Constants.CUSTOM_LOGS_SCREEN ->{
                intent.putExtra(Constants.SCREEN_ID,Constants.CUSTOM_LOGS_SCREEN)
            }
        }

        // Start the activity with the selected option
        startActivity(intent)
    }
}
