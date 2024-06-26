package com.duckyroute.duckyroute.data.remote.api.tokensession

import com.duckyroute.duckyroute.domain.model.ResponseToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenSessionApiClient {
    @POST("/usuario/user-token")
    suspend fun getNewAccessToken(@Body request: ResponseToken): Response<ResponseToken>
}