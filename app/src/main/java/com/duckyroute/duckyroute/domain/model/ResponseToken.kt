package com.duckyroute.duckyroute.domain.model

import com.google.gson.annotations.SerializedName

data class ResponseToken(
    @SerializedName("token")
    val token: String
)
