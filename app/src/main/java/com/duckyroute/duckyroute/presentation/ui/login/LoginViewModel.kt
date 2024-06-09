package com.duckyroute.duckyroute.presentation.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duckyroute.duckyroute.data.local.preferences.PreferencesManagerService.Companion.preferencesManager
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import com.duckyroute.duckyroute.domain.model.UserSessionRequest
import com.duckyroute.duckyroute.domain.model.UserSessionResponse
import com.duckyroute.duckyroute.domain.usecase.GetUserSessionUseCase
import kotlinx.coroutines.launch
import java.io.IOException

class LoginViewModel: ViewModel() {

    var getUserSession = GetUserSessionUseCase()
    val loginResult = MutableLiveData<ResponseStatus>()

    fun iniciarSession(email: String, password: String){

        if (!validarCampos(email, password))
            return

        viewModelScope.launch {
            try {
                val request = UserSessionRequest(email, password)
                val response = getUserSession(request)

                if (response != null) {
                    if(saveUserSession(response)){
                        loginResult.postValue(ResponseStatus.SUCCESS)
                    }
                } else {
                    loginResult.postValue(ResponseStatus.INCORRECT)
                }
            } catch (e: IOException) {
                loginResult.postValue(ResponseStatus.ERROR)
            }
        }
    }

    private fun saveUserSession(response: UserSessionResponse): Boolean = preferencesManager.saveUserSession(response)

    private fun validarCampos(email: String, password: String): Boolean{

        if(email.isBlank() || password.isBlank()){
            loginResult.postValue(ResponseStatus.EMPTY)
            return false
        }

        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

        if(!email.matches(emailRegex)){
            loginResult.postValue(ResponseStatus.INVALID_EMAIL)
            return false
        }

        return true
    }
}