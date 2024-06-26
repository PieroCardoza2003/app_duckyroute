package com.duckyroute.duckyroute.data.remote.api.chatbot

import com.duckyroute.duckyroute.domain.model.ChatbotMessage
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatbotApiClient {

    @POST("/api/chatbot")
    suspend fun chatbot(@Body request: ChatbotMessage):Response<ChatbotMessage>

}