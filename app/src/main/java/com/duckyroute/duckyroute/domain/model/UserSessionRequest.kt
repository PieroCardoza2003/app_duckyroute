package com.duckyroute.duckyroute.domain.model

import com.google.gson.annotations.SerializedName

data class UserSessionRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("contrasena")
    val contrasena: String
)
