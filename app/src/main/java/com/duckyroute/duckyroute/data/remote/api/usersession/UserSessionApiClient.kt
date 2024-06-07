package com.duckyroute.duckyroute.data.remote.api.usersession

import com.duckyroute.duckyroute.domain.model.UserSessionRequest
import com.duckyroute.duckyroute.domain.model.UserSessionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserSessionApiClient {

    @POST("/usuario/login")
    suspend fun getUserSession(@Body request: UserSessionRequest ): Response<UserSessionResponse>


}