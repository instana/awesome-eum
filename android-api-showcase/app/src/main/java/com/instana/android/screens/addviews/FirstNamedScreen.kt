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
class FirstNamedScreen:BaseFragment<NameViewBaseBinding>() {
    private lateinit var sharedPreferences: MyPreferences
    private var buttonClickCallback: (() -> Unit)? = null

    companion object {
        /**
         * Create a new instance of FirstNamedScreen with the provided buttonClickCallback.
         *
         * @param buttonClickCallback Callback function to be executed when the button is clicked.
         * @return The created instance of FirstNamedScreen.
         */
        fun newInstance(buttonClickCallback: () -> Unit): FirstNamedScreen {
            val fragment = FirstNamedScreen()
            fragment.buttonClickCallback = buttonClickCallback
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize shared preferences
        sharedPreferences = MyPreferences(requireContext().applicationContext)

        // Retrieve the saved name from shared preferences
        val savedName = sharedPreferences.getValue(Constants.SCREEN_NAME_1,"").toString()

        with(binding){
            if(savedName.isNotEmpty()){
                screenNameProvided.text = savedName

                // Set the view name for Instana monitoring
                Instana.view = savedName
            }

            saveScreenNameBtn.setOnClickListener {
                updateScreenName(screenNameUserInput.text.toString())
            }

            screenNav.text = getString(R.string.next_screen_inline_code_string)
            screenNav.setOnClickListener {
                buttonClickCallback?.invoke()
            }
        }
    }

    /**
     * Update the screen name with the provided viewName.
     *
     * @param viewName The new screen name to be saved and displayed.
     */
    private fun updateScreenName(viewName:String){
        sharedPreferences.setValue(Constants.SCREEN_NAME_1,viewName)
        binding.screenNameProvided.text = viewName

        // Set the view name for Instana monitoring
        Instana.view = viewName
    }
}
