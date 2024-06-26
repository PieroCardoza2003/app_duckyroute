package com.duckyroute.duckyroute.presentation.ui.home.myinformation.personal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duckyroute.duckyroute.MainApplication.Companion.preferencesManager
import com.duckyroute.duckyroute.domain.model.ChangePhoneRequest
import com.duckyroute.duckyroute.domain.usecase.ChangePhoneUseCase
import kotlinx.coroutines.launch
import java.io.IOException

class TelefonoViewModel: ViewModel() {

    var changePhone = ChangePhoneUseCase()
    val result = MutableLiveData<String>()
    val data = MutableLiveData<ChangePhoneRequest>()

    fun updatePhone(codigoPais: String, numero: String){
        if(codigoPais.isBlank() || numero.isBlank()){
            result.postValue("Por favor, ingrese su número de teléfono")
            return
        }

        viewModelScope.launch {
            try {
                val session = preferencesManager.getUserSession()

                if (session != null) {
                    val response = changePhone(ChangePhoneRequest(session.usuarioId, codigoPais, numero))

                    if (response != null) {
                        data.postValue(ChangePhoneRequest(-1, codigoPais, numero))
                        result.postValue("Se actualizó su número de teléfono")
                        return@launch
                    }
                }
                result.postValue("No se pudo actualizar su número de teléfono")
            } catch (ex: IOException){
                result.postValue("No se pudo actualizar su número de teléfono")
            }
        }

    }

}