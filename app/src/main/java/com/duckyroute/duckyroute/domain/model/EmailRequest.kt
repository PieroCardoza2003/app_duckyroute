package com.duckyroute.duckyroute.domain.model

import com.google.gson.annotations.SerializedName

data class EmailRequest(
    @SerializedName("email")
    val email: String
)
