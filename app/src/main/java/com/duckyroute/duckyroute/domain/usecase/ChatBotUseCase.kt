package com.duckyroute.duckyroute.domain.usecase

import com.duckyroute.duckyroute.data.repository.remote.ChatbotApiRepository
import com.duckyroute.duckyroute.domain.model.ChatbotMessage

class ChatBotUseCase {

    private val apiRepository = ChatbotApiRepository()

    suspend operator fun invoke(message: ChatbotMessage): ChatbotMessage? = apiRepository.chatBot(message)
}