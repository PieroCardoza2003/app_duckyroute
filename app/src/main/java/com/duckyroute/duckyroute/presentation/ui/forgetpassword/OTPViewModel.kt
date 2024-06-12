package com.duckyroute.duckyroute.presentation.ui.forgetpassword


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duckyroute.duckyroute.domain.model.ResponseStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

class OTPViewModel: ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    val verificationResult = MutableLiveData<ResponseStatus>()

    private var count: Int = 0

    fun verifyCode(code: String){
        try {
            viewModelScope.launch {
                isLoading.postValue(true)

                delay(2000)

                if(code == "12345"){
                    verificationResult.postValue(ResponseStatus.SUCCESS)
                }else{
                    if(++count >= 3)
                        verificationResult.postValue(ResponseStatus.TIMEOUT)
                    else
                        verificationResult.postValue(ResponseStatus.INCORRECT)
                }
                isLoading.postValue(false)
            }
        } catch (ex: IOException){
            verificationResult.postValue(ResponseStatus.ERROR)
            isLoading.postValue(false)
        }
    }
}