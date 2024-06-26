package com.duckyroute.duckyroute.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duckyroute.duckyroute.MainApplication.Companion.preferencesManager
import com.duckyroute.duckyroute.domain.usecase.GetConductorUseCase
import kotlinx.coroutines.launch
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import com.duckyroute.duckyroute.domain.model.local.Conductor
import com.duckyroute.duckyroute.domain.usecase.CloseSessionUseCase
import java.io.IOException

class HomeViewModel: ViewModel(){

    private lateinit var conductor: Conductor

    var getConductorUseCase = GetConductorUseCase()
    var closeSessionUseCase = CloseSessionUseCase()

    val result = MutableLiveData<ResponseStatus>()
    val loading = MutableLiveData<Boolean>()

    fun getConductor(): Conductor{
        return conductor
    }

    fun updateTelefono(codigoPais: String, numero: String){
        conductor = conductor.copy(codigo_pais = codigoPais, numero = numero)
    }

    fun onCreate() {
        loading.postValue(true)
        viewModelScope.launch {
            try {
                val session = preferencesManager.getUserSession()

                if (session != null) {
                    val response = getConductorUseCase(session.usuarioId)

                    if (response != null) {
                        conductor = response
                        result.postValue(ResponseStatus.SUCCESS)
                        return@launch
                    }
                }
                closeSession()
            } catch (ex: IOException) {
                result.postValue(ResponseStatus.ERROR)
            }
        }
        loading.postValue(false)
    }

    fun closeSession(){
        loading.postValue(true)
        viewModelScope.launch {
            try {
                closeSessionUseCase()
                result.postValue(ResponseStatus.EMPTY)
            } catch (io: IOException){
                result.postValue(ResponseStatus.ERROR)
            }
        }
        loading.postValue(false)
    }
}