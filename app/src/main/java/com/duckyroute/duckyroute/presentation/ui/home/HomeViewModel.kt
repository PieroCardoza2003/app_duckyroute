package com.duckyroute.duckyroute.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duckyroute.duckyroute.data.local.preferences.PreferencesManagerService.Companion.preferencesManager
import com.duckyroute.duckyroute.domain.model.ConductorResponse
import com.duckyroute.duckyroute.domain.usecase.GetConductorApiUseCase
import com.duckyroute.duckyroute.domain.usecase.SetConductorMemoryUseCase
import kotlinx.coroutines.launch
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import com.duckyroute.duckyroute.domain.usecase.GetConductorMemoryUseCase
import java.io.IOException

class HomeViewModel: ViewModel(){

    var getConductorApi = GetConductorApiUseCase()

    var setConductorMemory = SetConductorMemoryUseCase()
    var getConductorMemoryUseCase = GetConductorMemoryUseCase()

    private val _data = MutableLiveData<ConductorResponse>() // Modificable
    val data: LiveData<ConductorResponse> = _data // Observable

    val result = MutableLiveData<ResponseStatus>()

    fun onCreate() {
        viewModelScope.launch {
            try {
                val session = preferencesManager.getUserSession()

                if (session != null) {
                    val response = getConductorApi(session.usuarioId)
                    if (response != null) {
                        setConductorMemory(response)
                        _data.value = getConductorMemoryUseCase()
                        result.postValue(ResponseStatus.SUCCESS)
                        return@launch
                    }
                }
                preferencesManager.deleteUserSession()
                result.postValue(ResponseStatus.EMPTY)
            } catch (ex: IOException) {
                result.postValue(ResponseStatus.ERROR)
            }
        }
    }

}