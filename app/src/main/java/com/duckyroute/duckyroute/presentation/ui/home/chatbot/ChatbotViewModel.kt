package com.duckyroute.duckyroute.presentation.ui.home.chatbot

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duckyroute.duckyroute.domain.model.ChatbotMessage
import com.duckyroute.duckyroute.domain.usecase.ChatBotUseCase
import kotlinx.coroutines.launch
import java.io.IOException

class ChatbotViewModel: ViewModel() {


    var chatbot = ChatBotUseCase()
    val result = MutableLiveData<String>()

    fun sendMessage(message: String){
        viewModelScope.launch {
            try {
                val response = chatbot(ChatbotMessage(message))

                if (response != null) {
                    result.postValue(response.message)
                    return@launch
                }
                result.postValue("Lo siento, no puedo ayudarte en este momento")
            } catch (ex: IOException){
                result.postValue("Lo siento, no puedo ayudarte en este momento")
            }
        }
    }

}