package com.duckyroute.duckyroute.data.remote.api

import com.duckyroute.duckyroute.model.UserSessionResponse
import retrofit2.Response
import retrofit2.http.POST

interface LoginApiClient {

    @POST("/usuario/login")
    suspend fun getUserSession(): Response<UserSessionResponse>


}