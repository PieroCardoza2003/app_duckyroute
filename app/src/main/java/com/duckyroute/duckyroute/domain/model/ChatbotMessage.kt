package com.duckyroute.duckyroute.domain.model

import com.google.gson.annotations.SerializedName

data class ChatbotMessage(
    @SerializedName("prompt")
    val message: String
)
