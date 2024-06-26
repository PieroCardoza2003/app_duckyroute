package com.duckyroute.duckyroute.domain.model

import com.google.gson.annotations.SerializedName

data class CloseSessionRequest(
    @SerializedName("id")
    val id: Int
)
