package com.instana.android.screens.addviews

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.instana.android.Instana
import com.instana.android.R
import com.instana.android.databinding.NameViewBaseBinding
import com.instana.android.shared.BaseFragment
import com.instana.android.utils.Constants
import com.instana.android.utils.MyPreferences

@SuppressLint("Range")
class SecondNamedScreen:BaseFragment<NameViewBaseBinding>() {
    private lateinit var sharedPreferences: MyPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize shared preferences
        sharedPreferences = MyPreferences(requireContext().applicationContext)

        // Retrieve the saved name from shared preferences
        val savedName = sharedPreferences.getValue(Constants.SCREEN_NAME_2,"").toString()

        with(binding){
            if(savedName.isNotEmpty()){
                screenNameProvided.text = savedName

                // Set the view name for Instana monitoring
                Instana.view = savedName
            }

            saveScreenNameBtn.setOnClickListener {
                updateScreenName(screenNameUserInput.text.toString())
            }

            // Set the visibility of screenNav button to be invisible
            screenNav.visibility = View.INVISIBLE

            // Set the title for the second screen
            title.text = getString(R.string.second_screen_name_inline_code_string)
        }
    }

    /**
     * Update the screen name with the provided viewName.
     *
     * @param viewName The new screen name to be saved and displayed.
     */
    private fun updateScreenName(viewName:String){
        sharedPreferences.setValue(Constants.SCREEN_NAME_2,viewName)
        binding.screenNameProvided.text = viewName

        // Set the view name for Instana monitoring
        Instana.view = viewName
    }
}
