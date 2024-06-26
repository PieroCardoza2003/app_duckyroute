package com.duckyroute.duckyroute.data.repository.remote

import com.duckyroute.duckyroute.data.remote.api.chatbot.ChatbotService
import com.duckyroute.duckyroute.domain.model.ChatbotMessage

class ChatbotApiRepository {

    private val api = ChatbotService()

    suspend fun chatBot(message: ChatbotMessage): ChatbotMessage? {
        return api.chatBot(message)
    }

}