package com.duckyroute.duckyroute.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duckyroute.duckyroute.data.local.preferences.PreferencesManagerService.Companion.preferencesManager

class MainViewModel: ViewModel(){

    private val _isLogged = MutableLiveData<Boolean>() //se puede modificar
    val isLogged: LiveData<Boolean> = _isLogged // solo se puede observar

    private fun setIsLogged(state: Boolean){
        _isLogged.value = state
    }

    fun checkIsLogged(){
        setIsLogged(preferencesManager.checkUserSession())
    }

}