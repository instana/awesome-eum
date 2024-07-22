package com.ibm.instashop.screens.settings

import androidx.lifecycle.ViewModel
import com.ibm.instashop.common.Constants
import com.ibm.instashop.common.Constants.INSTANA_KEY
import com.ibm.instashop.common.Constants.INSTANA_URL
import com.ibm.instashop.data.local.DataManager
import com.ibm.instashop.data.network.BaseUrlInterceptor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataManager: DataManager,
    private val baseUrlInterceptor: BaseUrlInterceptor
) : ViewModel() {

    fun updateKeyUrl(key: String?, url: String?) {
        if (!key.isNullOrEmpty() && !url.isNullOrEmpty()) {
            dataManager.saveString(INSTANA_KEY, key)
            dataManager.saveString(INSTANA_URL, url)
        }
    }

    fun getKeyUrl(): Pair<String, String> {
        return dataManager.getString(INSTANA_KEY) to dataManager.getString(INSTANA_URL)
    }

    fun updateCurrentVersion(version:String){
        dataManager.saveString(Constants.CURRENT_API_VERSION,version)
    }

    fun getUserName():String{
        return dataManager.getString(Constants.USER_NAME)
    }

    fun getUserID():String{
        return dataManager.getString(Constants.USER_ID)
    }

    fun getUserEmail():String{
        return dataManager.getString(Constants.USER_EMAIL)
    }
}