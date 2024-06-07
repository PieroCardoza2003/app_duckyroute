package com.duckyroute.duckyroute.presentation.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duckyroute.duckyroute.data.local.preferences.PreferencesManagerService.Companion.preferencesManager
import com.duckyroute.duckyroute.domain.model.UserSessionRequest
import com.duckyroute.duckyroute.domain.model.UserSessionResponse
import com.duckyroute.duckyroute.domain.usecase.UserSessionUseCase
import kotlinx.coroutines.launch
import java.io.IOException

class LoginViewModel: ViewModel() {

    var getUserSession = UserSessionUseCase()
    val infoMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    val loginResult = MutableLiveData<Boolean>()

    fun iniciarSession(email: String, password: String){

        if (!validarCampos(email, password))
            return

        isLoading.postValue(true)

        viewModelScope.launch {
            try {
                val request = UserSessionRequest(email, password)
                val response = getUserSession(request)

                if (response != null) {
                    if(saveUserSession(response)){
                        infoMessage.postValue("OK userID: ${response.usuarioId}")
                        loginResult.postValue(true)
                    }
                } else {
                    infoMessage.postValue("Verifique su email y/o contraseña")
                }
            } catch (e: IOException) {
                infoMessage.postValue("Error de conexión. Por favor, verifica tu conexión a Internet.")
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    private fun saveUserSession(response: UserSessionResponse): Boolean = preferencesManager.saveUserSession(response)


    private fun validarCampos(email: String, password: String): Boolean{

        if(email.isBlank() || password.isBlank()){
            infoMessage.postValue("Los campos no pueden estar en blanco")
            return false
        }

        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

        if(!email.matches(emailRegex)){
            infoMessage.postValue("El email es incorrecto")
            return false
        }

        return true
    }

}