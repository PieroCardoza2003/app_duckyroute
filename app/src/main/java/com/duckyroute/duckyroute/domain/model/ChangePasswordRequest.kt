package com.duckyroute.duckyroute.domain.model

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("id_usuario")
    val idUsuario: Int,
    @SerializedName("oldpassword")
    val oldPassword: String,
    @SerializedName("newpassword")
    val newPassword: String
)
