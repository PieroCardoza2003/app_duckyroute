package com.duckyroute.duckyroute.data.remote.api.chatbot

import com.duckyroute.duckyroute.data.remote.api.RetrofitHelperAuth
import com.duckyroute.duckyroute.domain.model.ChatbotMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChatbotService {

    private val retrofit = RetrofitHelperAuth.getRetrofit()

    suspend fun chatBot(message: ChatbotMessage): ChatbotMessage?{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(ChatbotApiClient::class.java).chatbot(message)
            response.body()
        }
    }

}