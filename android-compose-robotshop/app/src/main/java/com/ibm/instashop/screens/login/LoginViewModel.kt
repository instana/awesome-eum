package com.ibm.instashop.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibm.instashop.business_unit.models.ResponseGeneric
import com.ibm.instashop.business_unit.models.User
import com.ibm.instashop.business_unit.usecases.UserUseCases
import com.ibm.instashop.common.Constants
import com.ibm.instashop.common.DataState
import com.ibm.instashop.common.EventState
import com.ibm.instashop.data.local.DataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val dataManager: DataManager
):ViewModel() {

    private val _loginState = MutableStateFlow(DataState())
    val loginState: StateFlow<DataState> = _loginState

    private val _createUserState = MutableStateFlow(DataState())
    val createUserState: StateFlow<DataState> = _createUserState

    fun validateUser(name:String,password:String){
        userUseCases.validateUser(User(name=name, password = password)).onEach { result->
            when (result) {
                is EventState.Loading -> {
                    _loginState.value = DataState(isLoading = true)
                }
                is EventState.Success -> {
                    try {
                        _loginState.value = DataState(data = result.data)
                    }catch (e:Exception){
                        _loginState.value =
                            DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                    }
                }
                is EventState.Error -> {
                    _loginState.value =
                        DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun createUser(userName:String,email:String,password:String){
        userUseCases.createUser(User(userName,email,password)).onEach { result->
            when (result) {
                is EventState.Loading -> {
                    _createUserState.value = DataState(isLoading = true)
                }
                is EventState.Success -> {
                    try {
                        _createUserState.value = DataState(data = result.data as ResponseGeneric)
                        dataManager.saveString(Constants.USER_NAME,userName)
                        dataManager.saveString(Constants.USER_EMAIL,email)
                        dataManager.saveString(Constants.USER_ID,result.data.message.hashCode().toString())
                    }catch (e:Exception){
                        _createUserState.value =
                            DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                    }
                }
                is EventState.Error -> {
                    _createUserState.value =
                        DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                }
            }
        }.launchIn(viewModelScope)
    }
}