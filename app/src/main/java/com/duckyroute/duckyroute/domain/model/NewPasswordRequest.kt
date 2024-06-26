package com.duckyroute.duckyroute.domain.model

import com.google.gson.annotations.SerializedName

data class NewPasswordRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val contrasena: String
)
