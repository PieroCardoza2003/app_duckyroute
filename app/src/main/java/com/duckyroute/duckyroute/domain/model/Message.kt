package com.duckyroute.duckyroute.domain.model

data class Message(
    val id: Int,
    val name: String,
    val message: String,
    val type: Int //0: send, 1: recive
)