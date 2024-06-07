package com.duckyroute.duckyroute.domain.model

import com.google.gson.annotations.SerializedName

data class UserSessionResponse(
    @SerializedName("id_usuario")
    val usuarioId: Int,
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)