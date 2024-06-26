package com.duckyroute.duckyroute.domain.model

import com.google.gson.annotations.SerializedName

data class ChangePhoneRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("codigo_pais")
    val codigo_pais: String,
    @SerializedName("numero")
    val numero: String
)
