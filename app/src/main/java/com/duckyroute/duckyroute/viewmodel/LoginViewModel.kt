package com.duckyroute.duckyroute.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult


    private fun setLoginResult(result: Boolean){
        _loginResult.value = result
    }


    private fun setMessage(message: String) {
        _message.value = message
    }


    fun iniciarSession(){
        viewModelScope.launch {
            delay(4000)
            val isValidCredentials = false

            if (isValidCredentials) {
                setMessage("Inicio de sesión exitoso")
            } else {
                setMessage("Credenciales inválidas")
            }
            setLoginResult(isValidCredentials)
        }
    }


    fun validarCampos(email: String, password: String): Boolean{

        if(email.isBlank() || password.isBlank()){
            setMessage("Complete los campos")
            return false
        }

        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

        if(!email.matches(emailRegex)){
            setMessage("El correo no es válido")
            return false
        }

        return true
    }

}