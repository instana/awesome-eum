package com.instana.android.screens.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.instana.android.Instana
import com.instana.android.databinding.ProfileScreenBinding
import com.instana.android.shared.BaseFragment
import com.instana.android.utils.Constants
import com.instana.android.utils.MyPreferences
import com.instana.android.utils.showInfoDialog

class ProfileFragment : BaseFragment<ProfileScreenBinding>() {

    private lateinit var sharedPreference: MyPreferences

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Instana.view = Constants.PROFILE_SCREEN
        sharedPreference = MyPreferences(requireActivity().applicationContext)
        initViewWithSavedData()

        with(binding) {
            infoBoxProfile.setOnClickListener {
                showInfoDialog(
                    "Profile Info",
                    "This will be used as logged in user details in the Instana dashboard, you can update the details and simulate another user login",
                    requireContext()
                )
            }

            saveUserDetailsBtn.setOnClickListener {
                updateUserData(
                    userName = userName.text.toString(),
                    userEmail = userEmail.text.toString(),
                    userId = userId.text.toString()
                )
            }
        }
    }

    /**
     * Initializes the view with the saved user data from SharedPreferences.
     */
    private fun initViewWithSavedData() {
        with(binding) {
            userName.setText(sharedPreference.getValue(Constants.USER_NAME, "").toString())
            userId.setText(sharedPreference.getValue(Constants.USER_ID, "").toString())
            userEmail.setText(sharedPreference.getValue(Constants.USER_EMAIL, "").toString())
        }
    }

    /**
     * Updates the user data and saves it to SharedPreferences and Instana.
     *
     * @param userName The updated user name.
     * @param userId The updated user ID.
     * @param userEmail The updated user email.
     */
    private fun updateUserData(userName: String, userId: String, userEmail: String) {
        sharedPreference.setValue(Constants.USER_NAME, userName)
        sharedPreference.setValue(Constants.USER_ID, userId)
        sharedPreference.setValue(Constants.USER_EMAIL, userEmail)
        Instana.userId = userId
        Instana.userName = userName
        Instana.userEmail = userEmail
        Toast.makeText(requireContext(), "Saved Profile Details", Toast.LENGTH_LONG).show()
    }
}
