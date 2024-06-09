package com.duckyroute.duckyroute.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duckyroute.duckyroute.data.local.preferences.PreferencesManagerService.Companion.preferencesManager

class MainViewModel: ViewModel(){

    private val _isLogged = MutableLiveData<Boolean>() // Modificable
    val isLogged: LiveData<Boolean> = _isLogged // Observable

    private fun setIsLogged(state: Boolean){
        _isLogged.value = state
    }

    fun checkIsLogged(){
        setIsLogged(preferencesManager.checkUserSession())
    }

}