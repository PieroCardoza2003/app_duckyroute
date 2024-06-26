package com.duckyroute.duckyroute.presentation.ui.forgetpassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duckyroute.duckyroute.MainApplication.Companion.preferencesManager
import com.duckyroute.duckyroute.domain.model.NewPasswordRequest
import com.duckyroute.duckyroute.domain.model.EmailRequest
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import com.duckyroute.duckyroute.domain.model.VerifyCodeRequest
import com.duckyroute.duckyroute.domain.usecase.NewPasswordUseCase
import com.duckyroute.duckyroute.domain.usecase.FindUserEmailUseCase
import com.duckyroute.duckyroute.domain.usecase.VerifyCodeUserUseCase
import com.duckyroute.duckyroute.utils.VerifyEmail
import kotlinx.coroutines.launch
import java.io.IOException

class ForgetPasswordViewModel: ViewModel() {

    private var count: Int = 0
    private var userEmail: String = ""
    private var token: String = ""

    var showPassword1: Boolean = false
    var showPassword2: Boolean = false

    val loadingEmail = MutableLiveData<Boolean>()
    val responseStatusEmail = MutableLiveData<ResponseStatus>()

    val loadingOTP = MutableLiveData<Boolean>()
    val responseStatusOTP = MutableLiveData<ResponseStatus>()

    val loadingPassword = MutableLiveData<Boolean>()
    val responseStatusPassword = MutableLiveData<ResponseStatus>()

    var findUserEmail = FindUserEmailUseCase()
    var verifyCodeUser = VerifyCodeUserUseCase()
    var newPassword = NewPasswordUseCase()


    fun verifiEmail(email: String){
        if (!VerifyEmail.isValid(email)){
            responseStatusEmail.postValue(ResponseStatus.INVALID_EMAIL)
            return
        }

        viewModelScope.launch {
            loadingEmail.postValue(true)
            try{
                val request = EmailRequest(email)
                val response = findUserEmail(request)

                if (response != null){
                    userEmail = email
                    responseStatusEmail.postValue(ResponseStatus.SUCCESS)
                }
                else {
                    responseStatusEmail.postValue(ResponseStatus.UNSUCCESS)
                }
            }catch (ex: IOException){
                responseStatusEmail.postValue(ResponseStatus.ERROR)
            }
            loadingEmail.postValue(false)
        }
    }

    fun verifyCode(code: String) {
        viewModelScope.launch {
            loadingOTP.postValue(true)
            try {
                val request = VerifyCodeRequest(userEmail, code)
                val response = verifyCodeUser(request)

                if (response != null) {
                    token = response.token
                    preferencesManager.saveKey("accessToken", token)
                    responseStatusOTP.postValue(ResponseStatus.SUCCESS)
                } else {
                    if (++count >= 3)
                        responseStatusOTP.postValue(ResponseStatus.TIMEOUT)
                    else
                        responseStatusOTP.postValue(ResponseStatus.UNSUCCESS)
                }
            } catch (ex: IOException) {
                responseStatusOTP.postValue(ResponseStatus.ERROR)
            }
            loadingOTP.postValue(false)
        }
    }

    fun verifyPassword(password: String, password2: String){
        if(password.isBlank() || password2.isBlank() || password != password2){
            responseStatusPassword.postValue(ResponseStatus.EMPTY)
            return
        }

        viewModelScope.launch {
            loadingPassword.postValue(true)
            try {
                val request = NewPasswordRequest(userEmail, password)
                val response = newPassword(request)

                if (response != null){
                    preferencesManager.deleteUserSession()
                    responseStatusPassword.postValue(ResponseStatus.SUCCESS)
                }
                else
                    responseStatusPassword.postValue(ResponseStatus.UNSUCCESS)
            }
            catch (ex: IOException){
                responseStatusPassword.postValue(ResponseStatus.ERROR)
            }
            loadingPassword.postValue(false)
        }
    }



}