package com.duckyroute.duckyroute.domain.model

import com.google.gson.annotations.SerializedName

data class VerifyCodeRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("code")
    val code: String
)
