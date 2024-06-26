package com.duckyroute.duckyroute.presentation.ui.home.security

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duckyroute.duckyroute.MainApplication.Companion.preferencesManager
import com.duckyroute.duckyroute.domain.model.ChangePasswordRequest
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import com.duckyroute.duckyroute.domain.usecase.ChangePasswordUseCase
import kotlinx.coroutines.launch
import java.io.IOException

class SecurityFragmentViewModel: ViewModel() {

    var showOldPassword: Boolean = false
    var showNewPassword: Boolean = false

    val responseStatus = MutableLiveData<ResponseStatus>()
    val loading = MutableLiveData<Boolean>()

    var changePassword = ChangePasswordUseCase()

    fun verifyPassword(oldPassword: String, newPassword: String){
        if(newPassword.isBlank() || oldPassword.isBlank()){
            responseStatus.postValue(ResponseStatus.EMPTY)
            return
        }

        loading.postValue(true)
        viewModelScope.launch {
            try {
                val session = preferencesManager.getUserSession()
                if(session != null){
                    val request = ChangePasswordRequest(session.usuarioId, oldPassword, newPassword)
                    val response = changePassword(request)

                    if (response != null){
                        responseStatus.postValue(ResponseStatus.SUCCESS)
                        return@launch
                    }
                }
                responseStatus.postValue(ResponseStatus.UNSUCCESS)
            } catch (ex: IOException){
                responseStatus.postValue(ResponseStatus.ERROR)
            }
        }
        loading.postValue(false)
    }
}